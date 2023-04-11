package com.example.domain.useCase

import com.example.domain.model.MovieItem
import com.example.domain.repository.MovieRepository
import com.example.domain.utils.DataState
import com.example.domain.utils.StateMessage
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    lateinit var moviesFinal: List<MovieItem>
    lateinit var state: StateMessage
    suspend operator fun invoke(): DataState<List<MovieItem>> {
return repository.getMovieTopRatedFromApi()
//        when (val a = repository.getMovieTopRatedFromApi()) {
//            is DataState.Data -> {
//                moviesFinal = a.data!!
//                state = a.response!!
//            }
//            is DataState.Error -> {
//                moviesFinal = a.data!!
//                state = a.response!!
//            }
//        }
    }

}