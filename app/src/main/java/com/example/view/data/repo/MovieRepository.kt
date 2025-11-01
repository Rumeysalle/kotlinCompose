package com.example.view.data.repo

import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.data.remote.response.MovieResponse
import com.example.view.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<MovieListResponse>
    suspend fun getMovieById(id: String): MovieDetailResponse

}

