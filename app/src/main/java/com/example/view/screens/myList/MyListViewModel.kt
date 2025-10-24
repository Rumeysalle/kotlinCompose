package com.example.view.screens.myList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.data.FavoriteManager
import com.example.view.screens.movieDetail.Movie
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyListViewModel : ViewModel() {

    val favorites: StateFlow<List<Movie>> = FavoriteManager.favorites


    /**
     * Belirli bir filmin favori olup olmadığını kontrol et
     */   fun removeFromFavorites(movieId: String) {
        viewModelScope.launch {
            FavoriteManager.removeFavorite(movieId)
        }
    }
}
