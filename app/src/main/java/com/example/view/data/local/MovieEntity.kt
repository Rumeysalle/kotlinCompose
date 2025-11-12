package com.example.view.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.view.data.remote.response.GenreResponse
import com.example.view.domain.model.Genre
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail



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


@Entity(tableName = "movie_details")
data class MovieDetailEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val runtime: Int,
    val rating: Double,
    val genres: List<Genre>,
    val isFavorite: Boolean = false,
    val adult: Boolean
)

@Entity(tableName = "movie_lists")
data class MovieListEntity(
    @PrimaryKey val page: Int,
    val movies: List<MovieDetailEntity>
)


fun MovieDetailEntity.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        overview = overview,
        poster_path = posterUrl,
        release_date = releaseDate,
        runtime = runtime,
        genres = genres,
        homepage = "",
        status = "",
        adult = false,
        backdrop_path = "",
        vote_average = rating

    )
}
fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        releaseDate = releaseDate,
        rating = rating,
        isFavorite = isFavorite,
        backdropPath = "",
        genreIds = emptyList()
    )
}






