package com.example.domain.useCase

import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieByKeywordUseCase {
    @Inject lateinit var repository: MovieRepository
//    suspend operator fun invoke(key:String): MovieByKeyword =repository.getMovieByKeyword(key)

}