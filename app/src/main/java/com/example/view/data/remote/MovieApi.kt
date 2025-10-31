package com.example.view.data.remote


import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String): MovieListResponse

    // Favori filmler
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("page") page: Int = 1,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): MovieResponse

    // Tek film detayı
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

}