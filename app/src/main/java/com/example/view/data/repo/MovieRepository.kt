package com.example.view.data.repo

import com.example.view.BuildConfig
import com.example.view.data.remote.MovieApiService
import com.example.view.data.remote.response.FavoriteResponse
import com.example.view.data.remote.response.GenreResponse
import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
): MovieRepository {

    override suspend fun getPopularMovies(page: Int): MovieListResponse{
        return movieApiService.getPopularMovies(apiKey = BuildConfig.TMDB_API_KEY, page = page)
    }
    override suspend fun getTopRatedMovies(page: Int): MovieListResponse{
        return movieApiService.getTopRatedMovies(apiKey = BuildConfig.TMDB_API_KEY,)

    }
    override suspend fun getUpcomingMovies(page: Int): MovieListResponse {
        return movieApiService.getUpcomingMovies(apiKey = BuildConfig.TMDB_API_KEY,)
    }
    override suspend fun getNowPlayingMovies(page: Int): MovieListResponse {
        return movieApiService.getNowPlayingMovies(apiKey = BuildConfig.TMDB_API_KEY,)
    }

    override suspend fun getGenres(apiKey: String): GenreResponse {
        return movieApiService.getGenres(apiKey = apiKey)
    }
    override suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieDetailResponse{
        return movieApiService.getMovieDetail(movieId = movieId, apiKey = apiKey)
    }
    override suspend fun getToggleFavoriteMovies(accountId: Int, sessionId: String): FavoriteResponse{
        return movieApiService.toggleFavoriteList(accountId = accountId, sessionId = sessionId)
    }

}

