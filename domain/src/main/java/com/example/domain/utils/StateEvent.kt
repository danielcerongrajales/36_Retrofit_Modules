package com.example.domain.utils


interface StateEvent {

    fun errorInfo(): String
//    fun errorInfos(): MoviesItemUiState
    fun eventName(): String
    fun shouldDisplayProgressBar(): Boolean
}
