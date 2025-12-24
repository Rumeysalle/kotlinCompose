package com.example.view.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.view.domain.model.Genre
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail
import java.util.Collections.emptyList


@Entity(tableName = "movies")
data class MovieLocal(
    @PrimaryKey val id: Int = 0,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val rating: Double,
    val isFavorite: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)








