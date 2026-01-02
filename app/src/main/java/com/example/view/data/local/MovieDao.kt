package com.example.view.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: MovieLocal)


    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllMovies(): Flow<List<MovieLocal>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieLocal?

    // Başlığa göre film arama
    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun searchMovies(query: String): Flow<List<MovieLocal>>


    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    fun observeFavorites(): Flow<List<MovieLocal>>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId AND isFavorite = 1)")
    suspend fun isMovieFavorite(movieId: Int): Boolean

    @Query("SELECT id FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteMovieIds(): List<Int>
}

