package com.example.view.domain.model

import android.icu.text.CaseMap.Title

data class MovieList(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
