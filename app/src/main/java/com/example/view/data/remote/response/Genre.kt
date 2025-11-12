package com.example.view.data.remote.response

import com.example.view.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

fun GenreResponse.toGenre(): List<Genre> {
    return genres.map { Genre(id = it.id, name = it.name) }
}


