package com.example.view
import HomeViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.view.data.AppContainer
import com.example.view.data.DefaultAppContainer

class MovieApplication: Application(){

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
}
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = (this[APPLICATION_KEY] as MovieApplication)
                val movieRepository = application.container.movieRepository
                HomeViewModel(movieRepository = movieRepository)
            }
        }
    }
}

