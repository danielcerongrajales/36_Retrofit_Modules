package com.example.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.network.MovieService
import java.io.IOException
import androidx.room.withTransaction
import com.example.data.database.MovieDatabase
import com.example.data.database.entities.RemoteKey
import com.example.data.extensions.toDatabase
import com.example.data.extensions.toDomain
import com.example.data.network.model.PopularMovies
import com.example.data.network.ApiResponseHandler
import com.example.data.network.NetworkResponse
import com.example.data.utils.*
import com.example.domain.model.MovieItem
import com.example.domain.utils.DataState
import com.example.domain.utils.MessageType
import com.example.domain.utils.StateMessage
import retrofit2.Response

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class ExampleRemoteMediator
@Inject constructor(
    private val apiService : MovieService,
    private val db: MovieDatabase
) : RemoteMediator<Int, MovieItem>() {
    private val startingPageIndex = 1
    private val remoteKeyDao = db.remoteKeyDao()
    private val movieDao = db.getMovieDao()
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieItem>): MediatorResult {
        return try {
            when (val page = getKeyPageData(loadType)) {
                is Page.PageSuccess -> {
                    val networkResult= safeApiCall(Dispatchers.IO) { apiService.getPopularMovies(page.valor) }
                    val response = dataState(networkResult)

                    db.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            movieDao.deleteAllMovies()
                        }
                        when (response) {
                            is DataState.Data -> {
                                val nextPage = if (response.data?.isEmpty() == true) {
                                    null
                                } else {
                                    page.valor + 1
                                }
                                val movieEntities = response.data?.map { it.toDatabase() }
                                remoteKeyDao.insertKey(
                                    RemoteKey(
                                        id = "discover_movie",
                                        next_page = nextPage,
                                        last_updated = System.currentTimeMillis()
                                    )
                                )
                                movieEntities?.let { resmovie ->

                                    resmovie.forEach { cal->
//                                        Log.d("cacamed","${cal.title} , $page , $nextPage")
                                     movieDao.getMovieById(cal.id).forEach {
                                         if (cal.id==it.id){
                                           Log.d("caca", "${cal.title}${cal.id.toString()}  ${it.title}${it.id.toString()} $page dede $nextPage")
                                       }
                                     }
                                   }
                                    movieDao.insertAll(resmovie)
                                }

                                MediatorResult.Success(endOfPaginationReached = response.data!!.isEmpty())
                            }
                            is DataState.Error -> MediatorResult.Error(Throwable(response.response.response?.message))
                            else -> {
                                MediatorResult.Error(Throwable("nul error"))
                            }
                        }


                    }

                }
                is Page.PageError -> page.MediatorResult
            }


        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: retrofit2.HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun dataState(networkResult: NetworkResponse<Response<PopularMovies>?>): DataState<List<MovieItem>>? {
        val response =
            object : ApiResponseHandler<List<MovieItem>, Response<PopularMovies>>(
                response = networkResult,
    //            stateEvent = null
            ) {
                override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<MovieItem>> {
                    return DataState.Data(
                        response = StateMessage(
                            com.example.domain.utils.Response(
                                message = "get from network",
                                messageType = MessageType.Success
                            )
                        ),
    //                    data= resultObj.body().results.
                        data = resultObj.body()?.results!!.map {
                            it.toDomain()
                        },
    //                    stateEvent = null
                    )
                }


            }.getResult()
        return response
    }


    private suspend fun getKeyPageData(loadType: LoadType): Page {
        return when (loadType) {
            LoadType.REFRESH -> {
                Page.PageSuccess(startingPageIndex)

            }
            LoadType.PREPEND -> {
                Page.PageError(MediatorResult.Success(true))

            }
            LoadType.APPEND -> {
                val remoteKey = db.withTransaction {
                    remoteKeyDao.getKeyByMovie("discover_movie")
                } ?: return Page.PageError(MediatorResult.Success(true))


                if (remoteKey.next_page == null) {
                    return Page.PageError(MediatorResult.Success(true))

                }
//                Log.d("TAG", "${remoteKey.toString()},${loadType.name}")
                Page.PageSuccess(remoteKey.next_page)


            }
        }


    }

    sealed class Page {
        data class PageSuccess(val valor: Int) : Page()
        data class PageError(val MediatorResult: MediatorResult) : Page()

    }
}



