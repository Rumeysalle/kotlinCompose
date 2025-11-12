package com.example.view.data


import com.example.view.data.remote.MovieApiService
import com.example.view.data.repo.MovieRepositoryImpl
import com.example.view.domain.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val movieRepository: MovieRepository
}
    class DefaultAppContainer : AppContainer {
        private val BASE_URL = "https://api.themoviedb.org/3/"

        private val retrofit:Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        private val retrofitService:MovieApiService by lazy {
            retrofit.create(MovieApiService::class.java)
        }


    override val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(retrofitService)
    }
    }