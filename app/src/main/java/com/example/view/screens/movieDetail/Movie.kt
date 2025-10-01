package com.example.view.screens.movieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val placeholderResId: Int? = null
){

}
