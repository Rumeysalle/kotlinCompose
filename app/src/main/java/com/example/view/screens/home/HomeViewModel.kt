
package com.example.view.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.util.Resource
import com.example.view.domain.model.Movie

import com.example.view.domain.usecase.getMoviesUseCase.GetNowPlayingUseCase
import com.example.view.domain.usecase.getMoviesUseCase.GetPopularUseCase
import com.example.view.domain.usecase.getMoviesUseCase.GetTopRatedUseCase
import com.example.view.domain.usecase.getMoviesUseCase.GetUpcomingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
) : ViewModel() {



    private val _upcomingMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val upcomingMovies: StateFlow<MovieListUiState> = _upcomingMovies

    private val _topRatedMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val topRatedMovies: StateFlow<MovieListUiState> = _topRatedMovies

    private val _nowPlayingMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val nowPlayingMovies: StateFlow<MovieListUiState> = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val popularMovies: StateFlow<MovieListUiState> = _popularMovies


    init {
        loadAllData()
    }

    private fun loadAllData() {
        fetchMovies(getUpcomingUseCase(page = 1), _upcomingMovies)
        fetchMovies(getTopRatedUseCase(page = 1), _topRatedMovies)
        fetchMovies(getNowPlayingUseCase(page = 1), _nowPlayingMovies)
        fetchMovies(getPopularUseCase(page = 1), _popularMovies)
    }

    // GENERIC
    // Her kategori için ayrı fonksiyon yazmak yerine tek bir generic fonksiyon
    private fun fetchMovies(
        flow: Flow<Resource<List<Movie>>>,
        targetState: MutableStateFlow<MovieListUiState>
    ) {
        viewModelScope.launch {
            flow.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        targetState.value = MovieListUiState.Loading
                    }
                    is Resource.Success -> {
                        targetState.value = MovieListUiState.Success(result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        targetState.value = MovieListUiState.Error(result.message ?: "Bilinmeyen hata")
                    }
                }
            }
        }
    }


}

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(val movies: List<Movie>) : MovieListUiState
    data class Error(val message: String) : MovieListUiState
}
