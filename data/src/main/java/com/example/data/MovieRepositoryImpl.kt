package com.example.data

import androidx.paging.ExperimentalPagingApi
import retrofit2.Response
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.database.CacheResponseHandler
import com.example.data.database.MovieDatabase
import com.example.data.database.entities.MovieDetailsEntity
import com.example.data.database.entities.MovieEntity
import com.example.a36_retrofit.data.network.model.*
import com.example.data.extensions.toDatabase
import com.example.data.extensions.toDomain
import com.example.data.network.ApiResponseHandler
import com.example.data.network.MovieService
import com.example.data.network.model.Credits
import com.example.data.network.model.MovieById
import com.example.data.network.model.PopularMovies
import com.example.data.utils.*
import com.example.domain.model.CreditsItem
import com.example.domain.model.MovieDetailsItem
import com.example.domain.model.MovieItem
import com.example.domain.repository.MovieRepository
import com.example.domain.utils.DataState
import com.example.domain.utils.MessageType
import com.example.domain.utils.StateMessage
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieService,
    private val local: MovieDatabase,
    private val exampleRemoteMediator: ExampleRemoteMediator
): MovieRepository {

      override suspend fun getMoviesPopularFromApi(nextPage: Int): DataState<List<MovieItem>> {
        val networkResult = safeApiCall(Dispatchers.IO)  {api.getPopularMovies(1)}
          val response = object: ApiResponseHandler<List<MovieItem>, Response<PopularMovies>>(
            response = networkResult,
//            stateEvent = null
        ){
            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<MovieItem>> {
                return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
//                    data= resultObj.body().results.
                    data =  resultObj.body()?.results!!.map {
                        it.toDomain()
                    },
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }

      override suspend fun getMoviesPopularFromLocal(search: String): DataState<List<MovieItem>> {
        TODO("Not yet implemented")
    }

    suspend fun getPopularMoviesFromLocal(): DataState<List<MovieItem>> {

        val cacheResult = safeCacheCall(Dispatchers.IO){
            local.getMovieDao().getAllMovies()
        }

        val response = object: CacheResponseHandler<List<MovieItem>, List<MovieEntity>>(
            response = cacheResult
//            stateEvent = null
        ){

            override suspend fun handleSuccess(resultObj: List<MovieEntity>): DataState<List<MovieItem>> {
                return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from local",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.map{it.toDomain()}
//                    stateEvent = null
                )
            }

        }.getResult()

        return response!!


    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesPopularPager() : Pager<Int, MovieItem> {
        val dbSource = {
            local.getMovieDao().pagingSource()
        }
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator =   exampleRemoteMediator,
            pagingSourceFactory = dbSource
        )

    }
      override suspend fun insertMovies(list:List<MovieItem>){
//        =local.getMovieDao().insertAll(list)
    }
      override suspend fun clearMovies() =local.getMovieDao().deleteAllMovies()

//    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
//    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
//    suspend fun getMovieById(id:Int): MovieById = api.getMovieById(id)

      override suspend fun clearDetailMovie() =local.getMovieDetailsDao().deleteAllMovies()
      override suspend fun insertMovieDetails(list: MovieDetailsItem) =local.getMovieDetailsDao().insertMovie(list.toDatabase())
      override suspend fun getMovieDetailsFromApi(nextPage: Int): DataState<MovieDetailsItem> {
        val networkResult = safeApiCall(Dispatchers.IO)  { api.getMovieById(nextPage)}
        val response = object: ApiResponseHandler<MovieDetailsItem, Response<MovieById>>(
            response = networkResult,
//            stateEvent = null
        ){
//            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
//                return DataState.Data(
//                    response = StateMessage(Response(
//                        message = "get from network",
//                        messageType = MessageType.Success
//                    )),
////                    data= resultObj.body().results.
//                    data =  resultObj.body()?.results!!.map {
//                        it.toDomain()
//                    },
////                    stateEvent = null
//                )
//            }

            override suspend fun handleSuccess(resultObj: Response<MovieById>): DataState<MovieDetailsItem>? {
                return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.toDomain(),
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }
      override suspend fun getMovieDetailsFromLocal(nextPage: Int): DataState<MovieDetailsItem> {

        val cacheResult = safeCacheCall(Dispatchers.IO){
            local.getMovieDetailsDao().getDetailsMovie(nextPage)
        }

        val response = object: CacheResponseHandler<MovieDetailsItem, MovieDetailsEntity>(
            response = cacheResult
//            stateEvent = null
        ){

//            override suspend fun handleSuccess(resultObj: List<MovieResultEntity>): DataState<MovieDetails> {
//
//            }

            override suspend fun handleSuccess(resultObj: MovieDetailsEntity): DataState<MovieDetailsItem>? {
               return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from local",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.toDomain()
//                    stateEvent = null
                )
            }

        }.getResult()

        return response!!


    }

      override suspend fun getMovieCreditsApi(movieId: Int): DataState<CreditsItem> {
        val networkResult = safeApiCall(Dispatchers.IO)  { api.getCredits(movieId)}
        val response = object: ApiResponseHandler<CreditsItem, Response<Credits>>(
            response = networkResult,
//            stateEvent = null
        ){
//            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
//                return DataState.Data(
//                    response = StateMessage(Response(
//                        message = "get from network",
//                        messageType = MessageType.Success
//                    )),
////                    data= resultObj.body().results.
//                    data =  resultObj.body()?.results!!.map {
//                        it.toDomain()
//                    },
////                    stateEvent = null
//                )
//            }

            override suspend fun handleSuccess(resultObj: Response<Credits>): DataState<CreditsItem>? {
                return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.toDomain(),
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }

    override suspend fun getMovieTopRatedFromApi(): DataState<List<MovieItem>> {
        val networkResult = safeApiCall(Dispatchers.IO)  { api.getMovieTopRated()}
        val response = object: ApiResponseHandler<List<MovieItem>, Response<MovieTopRated>>(
            response = networkResult,
//            stateEvent = null
        ){
//            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
//                return DataState.Data(
//                    response = StateMessage(Response(
//                        message = "get from network",
//                        messageType = MessageType.Success
//                    )),
////                    data= resultObj.body().results.
//                    data =  resultObj.body()?.results!!.map {
//                        it.toDomain()
//                    },
////                    stateEvent = null
//                )
//            }



            override suspend fun handleSuccess(resultObj: Response<MovieTopRated>): DataState<List<MovieItem>>? {
               return DataState.Data(
                    response = StateMessage(
                        com.example.domain.utils.Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.results?.map {
                        it.toDomain()
                    },
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!
    }
//    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
//suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}



