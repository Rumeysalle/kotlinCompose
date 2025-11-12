import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.BuildConfig
import com.example.view.data.remote.response.toMovieList
import com.example.view.domain.model.Genre
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieList
import com.example.view.domain.repository.MovieRepository
import com.example.view.screens.MovieCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    // CATEGORY STATE (senin orijinal yapın)
    private val _upcomingMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val upcomingMovies = _upcomingMovies

    private val _topRatedMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val topRatedMovies = _topRatedMovies

    private val _nowPlayingMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val nowPlayingMovies = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val popularMovies = _popularMovies

    // Genre için ayrı UI STATE
    private val _genres = MutableStateFlow<GenreUiState>(GenreUiState.Loading)
    val genres: StateFlow<GenreUiState> = _genres

    // seçili genre
    private val _selectedGenre = MutableStateFlow<Int?>(null)
    val selectedGenre: StateFlow<Int?> = _selectedGenre


    init {
        loadAllMovieCategories()
        loadGenres()
    }

    // ---------------- GENRE LOAD ----------------

    private fun loadGenres(){
        viewModelScope.launch {
            _genres.value = GenreUiState.Loading

            try {
                val response = movieRepository.getGenres(apiKey = BuildConfig.TMDB_API_KEY)

                val genreList = response.genres
                    .map { Genre(it.id, it.name) }
                    .sortedBy { it.name }

                _genres.value = GenreUiState.Success(genreList)

            } catch (e: IOException) {
                _genres.value = GenreUiState.Error
            } catch (e: HttpException) {
                _genres.value = GenreUiState.Error
            }
        }
    }

    // ---------------- MOVIE LOAD ----------------

    private fun loadAllMovieCategories() {
        loadMovies(MovieCategory.UPCOMING)
        loadMovies(MovieCategory.TOP_RATED)
        loadMovies(MovieCategory.NOW_PLAYING)
        loadMovies(MovieCategory.POPULAR)
    }

    private fun loadMovies(category: MovieCategory, page: Int = 1) {
        viewModelScope.launch {

            val targetStateFlow = when (category) {
                MovieCategory.UPCOMING -> _upcomingMovies
                MovieCategory.TOP_RATED -> _topRatedMovies
                MovieCategory.NOW_PLAYING -> _nowPlayingMovies
                MovieCategory.POPULAR -> _popularMovies
            }

            targetStateFlow.value = MovieListUiState.Loading

            try {
                val movieListResponse = when (category) {
                    MovieCategory.UPCOMING ->
                        movieRepository.getPopularMovies(page)

                    MovieCategory.TOP_RATED ->
                    movieRepository.getTopRatedMovies(page)

                    MovieCategory.NOW_PLAYING ->
                    movieRepository.getNowPlayingMovies(page)

                    MovieCategory.POPULAR ->
                     movieRepository.getPopularMovies(page)
                }

                targetStateFlow.value = MovieListUiState.Success(movieListResponse.toMovieList())

            } catch (e: IOException) {
                targetStateFlow.value = MovieListUiState.Error
            } catch (e: HttpException) {
                targetStateFlow.value = MovieListUiState.Error
            }
        }
    }

    // ------------ GENRE SELECT ---------------

    fun setSelectedGenre(id: Int?) {
        _selectedGenre.value = id
    }

    // -------- FILTERED MOVIES (TopBar için liste) ----------

    val filteredMovies: StateFlow<List<Movie>> =
        combine(popularMovies, selectedGenre) { popularState, genreId ->

            if (popularState !is MovieListUiState.Success) return@combine emptyList()

            val movies = popularState.movieList.movies

            if (genreId == null) movies
            else movies.filter { it.genreIds.contains(genreId) }

        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )
}
sealed interface MovieListUiState {
    data class Success(val movieList: MovieList) : MovieListUiState
    data object Error : MovieListUiState
    data object Loading : MovieListUiState
}

sealed interface GenreUiState {
    data class Success(val genres: List<Genre>) : GenreUiState
    data object Loading : GenreUiState
    data object Error : GenreUiState
}
