package com.example.view.data.remote


import android.graphics.Region
import com.example.view.data.local.MovieEntity
import com.example.view.data.remote.response.FavoriteResponse
import com.example.view.data.remote.response.GenreResponse
import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieListResponse

    // Favori filmler
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("page") page: Int = 1,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): MovieResponse

    @POST("account/{account_id}/favorite")
    suspend fun toggleFavoriteList(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
    ):FavoriteResponse

    // Tek film detayı
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String
    ):GenreResponse

}