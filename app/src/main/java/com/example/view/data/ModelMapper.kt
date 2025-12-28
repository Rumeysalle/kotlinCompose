package com.example.view.data

import com.example.view.data.local.MovieLocal
import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.data.remote.response.MovieResponse
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail
import com.example.view.domain.model.MovieList
import java.util.Collections

/**
 * Model dönüştürücü (Mapper) fonksiyonlar.
 *
 * Üç ana model tipi arasındaki köprüyü kurar:
 * 1. Movie/MovieDetail: External (Domain katmanı - UI'da gösterilen)
 * 2. MovieLocal: Local (Veritabanı/Room - Cihazda saklanan)
 * 3. MovieResponse: Network (Data katmanı - API'den gelen)
 */

// --- EXTERNAL (Movie) to LOCAL (MovieLocal) ---

fun Movie.toLocal(isFav: Boolean = true) = MovieLocal(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    releaseDate = releaseDate,
    rating = rating,
    isFavorite = isFav,
    updatedAt = System.currentTimeMillis()
)

fun List<Movie>.toLocal() = map { it.toLocal() }


// --- LOCAL (MovieLocal) to EXTERNAL (Movie) ---

fun MovieLocal.toExternal() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    releaseDate = releaseDate,
    rating = rating,
    isFavorite = isFavorite,
    backdropPath = "",
    genreIds = Collections.emptyList()
)

@JvmName("localToExternal")
fun List<MovieLocal>.toExternal() = map(MovieLocal::toExternal)


// --- NETWORK (MovieResponse) to LOCAL (MovieLocal) ---

fun MovieResponse.toLocal() = MovieLocal(
    id = id,
    title = title,
    overview = overview,
    posterUrl = "https://image.tmdb.org/t/p/w500${poster_path ?: ""}",
    releaseDate = release_date ?: "",
    rating = vote_average,
    isFavorite = false,
)

@JvmName("networkToLocal")
fun List<MovieResponse>.toLocal() = map(MovieResponse::toLocal)


// --- LOCAL (MovieLocal) to NETWORK (MovieResponse) ---

fun MovieLocal.toNetwork() = MovieResponse(
    id = id,
    title = title,
    overview = overview,
    poster_path = posterUrl,
    release_date = releaseDate,
    vote_average = rating,
    adult = false,
    backdrop_path = "",
    genre_ids = Collections.emptyList(),
    original_language = "",
    original_title = "",
    popularity = 0.0,
    video = false,
    vote_count = 0
)

fun List<MovieLocal>.toNetwork() = map(MovieLocal::toNetwork)


// --- ZİNCİRLEME VE ÖZEL DÖNÜŞÜMLER ---

// MovieResponse -> Movie (Domain)
fun MovieResponse.toExternal() = toLocal().toExternal()

@JvmName("networkToExternal")
fun List<MovieResponse>.toExternal() = map(MovieResponse::toExternal)

// MovieListResponse (API Listesi) -> MovieList (Domain Listesi)
fun MovieListResponse.toListExternal() = MovieList(
    page = page,
    movies = results.toExternal(), // results bir List<MovieResponse> olduğu için üstteki fonksiyonu kullandık
    totalPages = totalPages,
    totalResults = totalResults
)

// MovieDetailResponse (API Detay) -> MovieDetail (Domain Detay)
fun MovieDetailResponse.toDetailExternal() = MovieDetail(
    id = id,
    title = title,
    overview = overview ?: "",
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    vote_average = vote_average,
    adult = adult,
    backdrop_path = backdrop_path,
    genres = genres,
    homepage = homepage,
    runtime = runtime,
    status = status,
)

fun MovieDetail.toMovie() = Movie (
    id = id,
    title = title,
    overview = overview,
    posterUrl = poster_path,
    releaseDate = release_date,
    rating = vote_average,
    isFavorite = false,
    backdropPath = "" ,
    genreIds = Collections.emptyList()
)