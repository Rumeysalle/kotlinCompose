package com.example.view.domain.model

import com.example.view.data.remote.response.MovieDetailResponse


data class MovieDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val status: String,
    val title: String,
    val vote_average: Double
)


