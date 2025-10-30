package com.example.view.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    //  Yeni film ekleme veya güncelleme
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    // Tüm filmleri getir (Flow kullanırsak UI otomatik güncellenir)
    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    // Belirli bir film ID’sine göre detay çekme
    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?

    // Favori filmleri listeleme
    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY id DESC")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    //  Favori durumunu değiştirme (toggle gibi)
    @Query("UPDATE movies SET isFavorite = :isFav WHERE id = :movieId")
    suspend fun updateFavoriteStatus(movieId: Int, isFav: Boolean)

    // Film silme
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    // Başlığa göre film arama
    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun searchMovies(query: String): Flow<List<MovieEntity>>

    // Filmleri başlığa veya tarihe göre sıralama
    @Query(
        """
        SELECT * FROM movies
        ORDER BY 
        CASE WHEN :sortType = 'title' THEN title END ASC,
        CASE WHEN :sortType = 'date' THEN id END DESC
        """
    )
    fun sortMovies(sortType: String): Flow<List<MovieEntity>>
}
