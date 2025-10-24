package com.example.view.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieList(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val placeholderResId: Int?,
    var isFavorite: Boolean = false
)
