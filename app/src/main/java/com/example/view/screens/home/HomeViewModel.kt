package com.example.view.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.data.Movies
import com.example.view.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    // Filmleri saklayan state
    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movieList.value = Movies.movies
    }
    // Sadece veri sağlar.

}
}