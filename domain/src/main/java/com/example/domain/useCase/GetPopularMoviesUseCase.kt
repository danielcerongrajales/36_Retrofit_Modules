package com.example.domain.useCase

import androidx.paging.Pager
import com.example.domain.model.MovieItem
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository){

     operator fun invoke(): Pager<Int, MovieItem> {
      val ala= repository.getMoviesPopularPager()

         return ala
//        return   when(val moviesNetwork= repository.getPopularMoviesFromApi(0)){
//
//            is DataState.Data -> {
//                if(moviesNetwork.data?.isNotEmpty() == true){
//                    repository.clearMovies()
//                   repository.insertMovies(moviesNetwork.data!!.map { it.toDatabase() })
//
//                  return  repository.getPopularMoviesFromLocal()
//                }else{
//                  return   repository.getPopularMoviesFromLocal()
//                }
//            }
//            is DataState.Error ->      {
//                return   repository.getPopularMoviesFromLocal()
//                }
//            is DataState.Loading ->{moviesNetwork}
//        }
//
////       return  when(movies){
//
////            is RespuestaItem.Success -> {
////                movies.popularMovies as List<MovieResulEntity>
////                movies
////                repository.insertMovies(movies.popularMovies.results)
////                RespuestaItem.Success(movies.popularMovies)
////            }
////           is RespuestaItem.Loading ->RespuestaItem.Loading()
////           is RespuestaItem.Failure -> TODO()
////           is RespuestaItem.HttpErrors.BadGateWay -> TODO()
////           is RespuestaItem.HttpErrors.InternalServerError -> TODO()
////           is RespuestaItem.HttpErrors.RemovedResourceFound -> TODO()
////           is RespuestaItem.HttpErrors.ResourceForbidden -> TODO()
////           is RespuestaItem.HttpErrors.ResourceNotFound -> TODO()
////           is RespuestaItem.HttpErrors.ResourceRemoved -> TODO()
////           is RespuestaItem.NetworkException -> TODO()
////           else -> {
////               RespuestaItem.Loading()
////       }
//
//
//
//
////       }

    }



}