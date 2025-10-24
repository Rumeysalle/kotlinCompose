package com.example.view.data

import com.example.view.R
import com.example.view.screens.movieDetail.Movie

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object Movies {
    val movies = listOf(
        Movie(
            id = "1",
            title = "Memento",
            description = "A man with short-term memory loss attempts to track down his wife's murderer.",
            placeholderResId = R.drawable.memento,

        ),
        Movie(
            id = "2",
            title = "Esaretin Bedeli",
            description = "The story of a banker imprisoned for life for murder.",
            placeholderResId = R.drawable.esaretin_bedeli
        ),
        Movie(
            id = "3",
            title = "Inception",
            description = "A thief who steals corporate secrets through dreams.",
            placeholderResId = R.drawable.inception
        )
    )
}


/**
 * Uygulama genelinde favori filmleri yöneten tekil (singleton) bir nesne.
 * Bu, global bir liste görevi görür.
 */
object FavoriteManager {

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    val favorites: StateFlow<List<Movie>> = _favorites.asStateFlow()

    fun addFavorite(movie: Movie) {
        _favorites.update { currentFavorites ->
            if (currentFavorites.none { it.id == movie.id }) {
                currentFavorites + movie.copy(isFavorite = true)
            } else {
                currentFavorites
            }
        }
    }

    fun removeFavorite(movieId: String) {
        _favorites.update { currentFavorites ->
            currentFavorites.filterNot { it.id == movieId }
        }
    }

    fun isFavorite(movieId: String): Boolean {
        return _favorites.value.any { it.id == movieId }
    }

    /**
     * Başlangıçta veya her uygulamayı açışta mevcut favori durumlarını yüklemek için (isteğe bağlı).
     * Bu genellikle kalıcı bir depolamadan (veritabanı, SharedPreferences) okunur.
     * Şimdilik sadece simüle edilmiş filmlerden alalım.
     */
    fun initializeFavorites() {
        // Bu kısım normalde bir veritabanından veya SharedPreferences'tan yüklenecektir.
        // Şimdilik varsayılan olarak boş veya movies.movies'den isFavorite=true olanları alalım.
        _favorites.value = Movies.movies.filter { it.isFavorite }
    }
}
