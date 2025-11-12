package com.example.view.screens.movieDetail


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.data.local.toMovieDetail
import com.example.view.data.remote.response.toDetailEntity
import com.example.view.data.remote.response.toFavoriteList
import com.example.view.domain.model.FavoriteList
import com.example.view.domain.model.MovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import androidx.lifecycle.SavedStateHandle
import com.example.view.BuildConfig
import com.example.view.domain.repository.MovieRepository


class DetailViewModel(
 savedStateHandle: SavedStateHandle,
 private val movieRepository: MovieRepository
) : ViewModel() {
    private val movieId: Int = savedStateHandle["movieId"] ?: 0

    private val _movieUiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val movieUiState: StateFlow<MovieUiState> = _movieUiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        loadMovieById()
    }

    fun loadMovieById() {
        viewModelScope.launch {
            _movieUiState.value = MovieUiState.Loading

            try {
                val movieResult  = movieRepository.getMovieDetail(movieId = movieId, apiKey = BuildConfig.TMDB_API_KEY)
                    .toDetailEntity()
                    .toMovieDetail()
                Log.d("DETAIL","GENRES: ${movieResult.genres}")
                Log.d("DETAIL","MOVIE: ${movieResult}")

                _movieUiState.value = MovieUiState.Success(movieDetail = movieResult)

            } catch (e: IOException) {
                _movieUiState.value = MovieUiState.Error
            } catch (e: HttpException) {
                _movieUiState.value = MovieUiState.Error
            }
        }
    }

    /**
     * Favoriye ekleme veya kaldırma işlemini tek bir fonksiyonda toplayalım.
     * FavoriteManager'ı kullanarak işlemi gerçekleştirir.
     */
    fun toggleFavorite() {
        viewModelScope.launch {
            _movieUiState.value = MovieUiState.Loading

            try {
                val listResult = movieRepository.getToggleFavoriteMovies(accountId = 1, sessionId = "").toFavoriteList()
                _movieUiState.value = MovieUiState.Success(movieList = listResult)
            } catch (e: IOException) {
                _movieUiState.value = MovieUiState.Error
            } catch (e: HttpException) {
                _movieUiState.value = MovieUiState.Error
            }
        }
    }


}
sealed interface MovieUiState {
    data class Success(
        val movieList: FavoriteList? = null,
        val movieDetail: MovieDetail? = null) : MovieUiState
    data object Error : MovieUiState
    data object Loading : MovieUiState
}