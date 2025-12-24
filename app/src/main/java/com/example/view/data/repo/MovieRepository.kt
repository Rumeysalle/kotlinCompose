package com.example.view.data.repo

import com.example.view.BuildConfig
import com.example.view.data.local.MovieDao

import com.example.view.data.remote.MovieApiService
import com.example.view.data.remote.response.MovieResponse
import com.example.view.data.toDetailExternal
import com.example.view.data.toExternal
import com.example.view.data.toLocal
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail
import com.example.view.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dao: MovieDao
): MovieRepository {

    // Yardımcı fonksiyon: API'den gelen listeyi favori durumuyla işaretler
    private suspend fun syncFavorites(apiMovies: List<MovieResponse>): List<Movie> {
        // Veritabanındaki favori ID'leri çek
        val favoriteIds = dao.getFavoriteMovieIds().toSet()

        // API'den gelen her filmi kontrol et
        return apiMovies.map { response ->
            val isFav = favoriteIds.contains(response.id)
            // Önce Entity'ye çevir, sonra favori durumunu güncelle, en son Domain'e çevir
            response.toLocal().copy(isFavorite = isFav).toExternal()
        }
    }

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        val response = movieApiService.getPopularMovies(BuildConfig.TMDB_API_KEY, page)
        return syncFavorites(response.results)
    }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        val response = movieApiService.getTopRatedMovies(BuildConfig.TMDB_API_KEY, page)
        return syncFavorites(response.results)
    }

    override suspend fun getUpcomingMovies(page: Int): List<Movie> {
        val response = movieApiService.getUpcomingMovies(BuildConfig.TMDB_API_KEY, page)
        return syncFavorites(response.results)
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Movie> {
        val response = movieApiService.getNowPlayingMovies(BuildConfig.TMDB_API_KEY, page)
        return syncFavorites(response.results)
    }

    override suspend fun insertFavorite(movie: Movie) {
        dao.upsert(movie.toLocal(isFav = true))
    }

    override suspend fun deleteFavorite(movieId: Int) {
        dao.deleteMovie(movieId)
    }

    override fun observeFavorites(): Flow<List<Movie>> {
        return dao.observeFavorites().map { entityList ->
            entityList.map { it.toExternal() }
        }
    }


    override suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieDetail {
        val response = movieApiService.getMovieDetail(movieId = movieId, apiKey = apiKey)
        return response.toDetailExternal()
    }
}
