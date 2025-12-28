package com.example.view.domain.usecase.getMoviesUseCase

import com.example.movieapp.domain.util.Resource
import com.example.view.domain.model.Movie
import com.example.view.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getTopRatedMovies(page)
            emit(Resource.Success(movies))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Bir hata oluştu"))
        }

    }
}