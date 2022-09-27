package com.example.domain.repository

import androidx.paging.Pager
import com.example.domain.model.CreditsItem
import com.example.domain.model.MovieItem
import com.example.domain.model.MovieDetailsItem
import com.example.domain.utils.DataState

interface MovieRepository {
    //popular movies domain
    suspend fun getMoviesPopularFromApi(offset:Int): DataState<List<MovieItem>>
    fun getMoviesPopularPager(): Pager<Int, MovieItem>
    //popular movies room
    suspend fun getMoviesPopularFromLocal(search:String): DataState<List<MovieItem>>
    suspend fun insertMovies(list:List<MovieItem>)


    //detail movie domain
    suspend fun getMovieDetailsFromApi(nextPage: Int): DataState<MovieDetailsItem>
    //detail movies room
    suspend fun getMovieDetailsFromLocal(nextPage: Int): DataState<MovieDetailsItem>
    suspend fun insertMovieDetails(list: MovieDetailsItem)

    //credits movie domain
    suspend fun getMovieCreditsApi(movieId: Int): DataState<CreditsItem>


    suspend fun getMovieTopRatedFromApi():DataState<List<MovieItem>>

    suspend fun clearDetailMovie()

    suspend fun clearMovies()

}