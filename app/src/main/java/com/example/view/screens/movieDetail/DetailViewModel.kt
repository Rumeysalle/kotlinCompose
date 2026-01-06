package com.example.view.screens.movieDetail



import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.domain.model.MovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import androidx.lifecycle.SavedStateHandle
import com.example.view.BuildConfig
import com.example.view.data.local.MovieDao
import com.example.view.data.toMovie
import com.example.view.domain.model.Movie
import com.example.view.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class DetailViewModel @Inject constructor(
 savedStateHandle: SavedStateHandle,
 private val movieRepository: MovieRepository,
) : ViewModel() {
    private val movieId: Int = savedStateHandle["movieId"] ?: 0

    private val _movieUiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val movieUiState: StateFlow<MovieUiState> = _movieUiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        loadMovieById()
        observeFavorite()
    }

    fun loadMovieById() {
        viewModelScope.launch {
            _movieUiState.value = MovieUiState.Loading

            try {
                val movieResult = movieRepository.getMovieDetail(
                    movieId = movieId,
                    apiKey = BuildConfig.TMDB_API_KEY
                )

                _movieUiState.value = MovieUiState.Success(movieDetail = movieResult)

            } catch (e: IOException) {
                _movieUiState.value = MovieUiState.Error
            } catch (e: HttpException) {
                _movieUiState.value = MovieUiState.Error
            }
        }
    }

    private fun observeFavorite() {
        viewModelScope.launch {
            movieRepository.observeFavorites().collectLatest { favorites ->
                _isFavorite.value = favorites.any { it.id == movieId }
            }
        }
    }

    fun onFavoriteClicked(movieDetail: MovieDetail) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                movieRepository.deleteFavorite(movieId)
            } else {
                movieRepository.insertFavorite(movieDetail.toMovie())
            }

        }
    }

    fun onPlayClick(movieId: Int, onKeyReceived: (String) -> Unit) {
        viewModelScope.launch {
            try {

                val videos = movieRepository.getMovieVideos(
                    movieId = movieId,
                    apiKey = BuildConfig.TMDB_API_KEY
                )

                val selectedVideo = videos.find { it.type == "Trailer" && it.site == "YouTube" }
                    ?: videos.firstOrNull()

                val key = selectedVideo?.key

                if (key != null) {
                    Log.d("PlayDebug", "Video key bulundu: $key")
                    onKeyReceived(key)
                } else {
                    Log.d("PlayDebug", "Video listesi boş veya uygun video bulunamadı")
                }

            }
            catch (e: IOException) {
                Log.e("PlayDebug", "İnternet hatası: ${e.message}")
            } catch (e: Exception) {
                Log.e("PlayDebug", "Beklenmedik bir hata oluştu: ${e.message}")
            }
        }
    }
}
    sealed interface MovieUiState {
        data class Success(
            val movieDetail: MovieDetail
        ) : MovieUiState

        data object Error : MovieUiState
        data object Loading : MovieUiState
    }
