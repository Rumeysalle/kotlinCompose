package com.example.view.domain.repository



import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun observeFavorites(): Flow<List<Movie>>

    suspend fun insertFavorite(movie: Movie)

    suspend fun deleteFavorite(movieId: Int)

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun getUpcomingMovies(page: Int): List<Movie>

    suspend fun getNowPlayingMovies(page: Int): List<Movie>

    suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieDetail
}



