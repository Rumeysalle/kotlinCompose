package com.example.view.screens.movieDetail

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val placeholderResId: Int? = null // Drawable'daki resmin resource ID'si
){

}
