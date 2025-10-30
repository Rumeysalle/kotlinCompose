package com.example.view.data.repo

import com.example.view.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieById(id: String): Movie?

}
class MovieRepositoryImpl : MovieRepository{
    override suspend fun getMovies(): List<Movie> {

        return TODO("Provide the return value")
    }

    override suspend fun getMovieById(id: String): Movie? {
         return TODO("Not yet implemented")
    }
}