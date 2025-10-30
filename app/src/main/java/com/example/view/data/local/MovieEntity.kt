package com.example.view.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.view.domain.model.Movie


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val rating: Double,
    val isFavorite: Boolean = false
)


fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        releaseDate = releaseDate,
        rating = rating
    )
}

