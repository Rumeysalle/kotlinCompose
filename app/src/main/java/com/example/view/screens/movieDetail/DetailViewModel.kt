package com.example.view.screens.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.data.FavoriteManager // FavoriteManager'ı içe aktardık
import com.example.view.data.Movies // Mevcut Movies data kaynağınız
import com.example.view.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    // Ekranda gösterilecek tek filmi tutar
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    /**
     * Favori filmleri Movie türünde tutan StateFlow artık doğrudan FavoriteManager'dan geliyor.
     * Bu sayede, FavoriteManager güncellendiğinde buradaki favorites de otomatik olarak güncellenecek.
     */
    val favorites: StateFlow<List<Movie>> = FavoriteManager.favorites

    // Seçilen filmin favori durumunu UI için ayrı tutabiliriz
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun loadMovieById(movieId: String) {
        viewModelScope.launch {
            val movie = Movies.movies.find { it.id == movieId }
            _selectedMovie.value = movie
            // Film yüklendiğinde, FavoriteManager'dan favori durumunu kontrol et
            _isFavorite.value = FavoriteManager.isFavorite(movieId)
        }
    }

    /**
     * Favoriye ekleme veya kaldırma işlemini tek bir fonksiyonda toplayalım.
     * FavoriteManager'ı kullanarak işlemi gerçekleştirir.
     */
    fun toggleFavorite(movieId: String) {
        viewModelScope.launch {
            val movie = Movies.movies.find { it.id == movieId }

            movie?.let {
                if (FavoriteManager.isFavorite(movieId)) {
                    // Eğer favorilerdeyse, FavoriteManager'dan kaldır
                    FavoriteManager.removeFavorite(movieId)
                    _isFavorite.value = false // UI durumu güncelle
                } else {
                    // Favorilerde değilse, FavoriteManager'a ekle
                    FavoriteManager.addFavorite(it)
                    _isFavorite.value = true // UI durumu güncelle
                }
            }
        }
    }


}