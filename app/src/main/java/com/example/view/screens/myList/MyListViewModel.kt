package com.example.view.screens.myList


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.view.domain.model.Movie
import com.example.view.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val favorites: StateFlow<List<Movie>> =
        repository.observeFavorites()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    // Edit modu basit toggle
    private val _isEditMode = kotlinx.coroutines.flow.MutableStateFlow(false)
    val isEditMode: StateFlow<Boolean> = _isEditMode

    fun toggleEditMode() {
        _isEditMode.value = !_isEditMode.value
    }

    fun deleteFavorite(movieId: Int) {
        viewModelScope.launch {
                repository.deleteFavorite(movieId)
            }
        }
}
