package com.example.view.domain.model

data class MovieList(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
data class FavoriteList(
    val isSuccess: Boolean,
    val message: String
)