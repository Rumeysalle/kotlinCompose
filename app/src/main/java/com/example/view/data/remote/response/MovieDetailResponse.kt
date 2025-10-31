package com.example.view.data.remote.response

import com.example.view.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: Any,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdb_id: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
)


fun MovieDetailResponse.toDomain(): MovieDetail {
    return MovieDetail(
        adult= adult,
        backdrop_path = backdrop_path?.let { "https://image.tmdb.org/t/p/w780$it" } ?: "",
        genres = genres.map { it.name },
        homepage = homepage,
        id = id,
        overview = overview,
        poster_path = poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        release_date = release_date,
        runtime = runtime,
        status = status,
        title = title,
    )
}