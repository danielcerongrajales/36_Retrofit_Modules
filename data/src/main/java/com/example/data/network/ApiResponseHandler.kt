package com.example.data.network

import com.example.data.network.NetworkErrors.NETWORK_DATA_NULL
import com.example.data.network.NetworkErrors.NETWORK_ERROR
import com.example.domain.utils.DataState
import com.example.domain.utils.MessageType
import com.example.domain.utils.Response
import com.example.domain.utils.StateMessage


abstract class ApiResponseHandler <ViewState, Data>(
    private val response: NetworkResponse<Data?>,
//    private val stateEvent: StateEvent?
){

    suspend fun getResult(): DataState<ViewState>? {

        return when(response){

            is NetworkResponse.GenericError -> {
                DataState.Error(
                    response = StateMessage(
                        Response(
//                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage.toString()}",
                            message = "Reason: ${response.errorMessage.toString()}",

                            messageType = MessageType.Error
                        )
                    ),
//                    stateEvent = stateEvent
                )
            }

            is NetworkResponse.NetworkError -> {
                DataState.Error(
                    response = StateMessage(
                        Response(
                            message = "Reason: $NETWORK_ERROR",
//                        message = "${stateEvent?.errorInfo()} Reason: $NETWORK_ERROR",

                            messageType = MessageType.Error
                        )
                    ),
//                    stateEvent = stateEvent
                )
            }

            is NetworkResponse.Success -> {
                if(response.value == null){
                    DataState.Error(
                        response = StateMessage(
                            Response(
                                message = "Reason: ${NETWORK_DATA_NULL}.",
//                            message = "${stateEvent?.errorInfo()}\n\nReason: ${NETWORK_DATA_NULL}.",
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
//            NetworkResponse.Finished -> DataState.Loading()
//            NetworkResponse.Loading -> DataState.Loading()
        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}