package com.example.view.domain.usecase.getMovieUseCase

import com.example.view.domain.repository.MovieRepository
import javax.inject.Inject

class getMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
}