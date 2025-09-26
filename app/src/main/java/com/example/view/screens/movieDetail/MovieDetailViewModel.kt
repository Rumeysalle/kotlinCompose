package com.example.view.screens.movieDetail

import androidx.lifecycle.ViewModel
import com.example.view.R

class MovieDetailViewModel: ViewModel(){
private val movies = listOf(
    Movie("1", "Memento", "desc...", placeholderResId = R.drawable.memento),
    Movie("2", "Esaretin Bedeli", "desc...", placeholderResId = R.drawable.esaretin_bedeli),
    Movie("3", "Inception", "desc...", placeholderResId = R.drawable.inception)
)
    fun getMovieById(movieId: String?): Movie?= movies.find { it.id == movieId }
}

