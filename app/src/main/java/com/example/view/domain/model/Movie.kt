package com.example.view.domain.model

import com.google.android.engage.video.datamodel.MovieEntity


data class Movie(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val rating: Double,
    val isFavorite: Boolean,
    val genreIds: List<Int>
)


