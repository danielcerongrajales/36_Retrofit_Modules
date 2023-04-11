package com.example.data.database

import com.example.data.database.CacheErrors.CACHE_DATA_NULL
import com.example.domain.utils.DataState
import com.example.domain.utils.MessageType
import com.example.domain.utils.Response
import com.example.domain.utils.StateMessage


abstract class CacheResponseHandler <ViewState, Data>(
    private val response: CacheResult<Data?>,
//    private val stateEvent: StateEvent?
){
    suspend fun getResult(): DataState<ViewState>? {

        return when(response){

            is CacheResult.GenericError -> {
                DataState.Error(
                    response = StateMessage(
                        Response(
                            message = "Reason: ${response.errorMessage}",
//                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage}",

                            messageType = MessageType.Error
                        )
                    ),
//                    stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                if(response.value == null){
                    DataState.Error(
                        response = StateMessage(
                            Response(
                                message = "Reason: ${CACHE_DATA_NULL}.",
//                            message = "${stateEvent?.errorInfo()}\n\nReason: ${CACHE_DATA_NULL}.",
                                messageType = MessageType.Error
                            )
                        ),
//                        stateEvent = stateEvent
                    )
                }
                else{
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}