package com.example.view.domain.usecase.detail

import com.example.movieapp.domain.util.Resource
import com.example.view.BuildConfig
import com.example.view.data.local.toMovieDetail
import com.example.view.data.remote.response.toDetailEntity
import com.example.view.data.remote.response.toMovie
import com.example.view.domain.model.Movie
import com.example.view.domain.model.MovieDetail
import com.example.view.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class getMoviedDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())

        try {
            val response = movieRepository.getMovieDetail(movieId = movieId, apiKey = BuildConfig.TMDB_API_KEY)
            val movieDetail = response.toDetailEntity().toMovieDetail()

            emit(Resource.Success(movieDetail))

        } catch (e: HttpException) {
            emit(Resource.Error<MovieDetail>(e.localizedMessage ?: "Bir hata oluştu"))
        } catch (e: IOException) {
            emit(Resource.Error<MovieDetail>("Sunucuya ulaşılamadı"))
        }
    }
}