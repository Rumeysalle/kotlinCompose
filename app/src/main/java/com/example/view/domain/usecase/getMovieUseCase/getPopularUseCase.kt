package com.example.view.domain.usecase.getMovieUseCase

import com.example.movieapp.domain.util.Resource
import com.example.view.data.remote.response.toMovie
import com.example.view.domain.model.Movie
import com.example.view.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getPopularUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            val response = movieRepository.getPopularMovies(page)
            val movie = response.results.map { it.toMovie() }

            emit(Resource.Success(movie))

        } catch (e: HttpException) {
        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>("Sunucuya ulaşılamadı"))
        }
    }
}