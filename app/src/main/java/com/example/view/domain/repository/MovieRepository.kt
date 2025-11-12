package com.example.view.domain.repository

import com.example.view.data.remote.response.FavoriteResponse
import com.example.view.data.remote.response.GenreResponse
import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): MovieListResponse
    suspend fun getTopRatedMovies(page: Int): MovieListResponse
    suspend fun getUpcomingMovies(page: Int): MovieListResponse
    suspend fun getNowPlayingMovies(page: Int): MovieListResponse
    suspend fun getGenres(apiKey: String): GenreResponse
    suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieDetailResponse
    suspend fun getToggleFavoriteMovies(accountId: Int, sessionId: String): FavoriteResponse
}



