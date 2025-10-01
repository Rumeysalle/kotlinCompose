package com.example.view.data

import androidx.compose.runtime.mutableStateListOf
import com.example.view.screens.movieDetail.Movie

object FavoriteLists{
    val favoriteList = mutableStateListOf<String>()

    fun addMovie(movieId:String) {
        if(!favoriteList.contains(movieId)){
            favoriteList.add(movieId)
        }
    }
    fun removeMovie(movieId: String) {
        favoriteList.remove(movieId)
    }
}