package com.example.a36_retrofit_modules.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.domain.model.MovieItem
import com.example.domain.useCase.GetPopularMoviesUseCase
import com.example.domain.useCase.GetTopRatedMoviesUseCase
import com.example.domain.utils.DataState
import com.example.domain.utils.StateMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MovieViewModel @Inject constructor(val getPopularMoviesUseCase: GetPopularMoviesUseCase, val getTopRatedMovieUseCase: GetTopRatedMoviesUseCase) :
    ViewModel() {


    private val _stateTwo = MutableStateFlow(UiStateTwo())
    val stateTwo : StateFlow<UiStateTwo> get() = _stateTwo

 var popularMovies: Flow<PagingData<MoviesItemUiState>> = getPopularMoviesUseCase().flow
    .cachedIn(viewModelScope).map { pagingData ->
        pagingData.map {
//            Log.d("tag","${it.title}, ${it.popularity}")
            it.toUiState()
        }
    }

data class UiStateTwo(
    val loadingBar:Boolean=false,
    val topRatedMovies: DataState<List<MoviesItemUiState>>?=null,
    val stateMessage: StateMessage?=StateMessage()
)




    init {


        viewModelScope.launch  {
            _stateTwo.value= _stateTwo.value.copy(loadingBar = true)
            _stateTwo.value = _stateTwo.value.copy(topRatedMovies =
               when( val a =getTopRatedMovieUseCase()){
                   is DataState.Data -> {
                        DataState.Data(a.response, a.data?.map{it.toUiState()})
                   }
                   is DataState.Error -> {
                       DataState.Error(a.response, a.data?.map{it.toUiState()})

                   }
               }

            )




            _stateTwo.value = _stateTwo.value.copy(loadingBar = false)
            }












    }





//private fun Update( lista: List<MoviesItemUiState>){
//    _stateTwo.value = _stateTwo.value.copy(topRatedMovies  =lista)
//}





    private fun MovieItem.toUiState() = MoviesItemUiState(
        this,
        onBookmark = {
            viewModelScope.launch {
//                switchMovieFavoriteUseCase(this@toUiState)
            }
        }
//        onShare = { TODO() },
//        onDelete = { TODO() }
    )

    fun changeLoading() {
        viewModelScope.launch {
            _stateTwo.value= _stateTwo.value.copy(loadingBar = true)

        }

    }


}


data class MoviesItemUiState(
    val movieItem: MovieItem,
    val onBookmark: () -> Unit
)

