package co.project.sumedhandroiddemo.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import co.project.sumedhandroiddemo.baseclasses.BaseViewModel
import co.project.sumedhandroiddemo.baseclasses.CardResponseWrapper
import co.project.sumedhandroiddemo.baseclasses.ResponseWrapper
import co.project.sumedhandroiddemo.repository.DashboardRepository
import co.project.sumedhandroiddemo.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository: DashboardRepository) :
        BaseViewModel(repository){
    private val _getResponse = MutableLiveData<ResponseWrapper>()
    val apiResponse = MutableLiveData<CardResponseWrapper>()

    init {
        if (!_getResponse.hasObservers()) observerApiResponse()
    }
    fun getTokenForCampaign() {
        repository.getCardResponse(_getResponse, Constants.ApiServiceId.CARD_API_SERVICE_ID)
    }

    private fun observerApiResponse() {
        _getResponse.observeForever { response ->
            if (response.statusCode != 200) {
                val errorResponseMsg =
                        if (response.error.isNotEmpty()) response.error else response.message
                sendResponseToView(
                        responseString = errorResponseMsg,
                        isSuccess = false,
                        serviceID = response.serviceID
                )
            } else {
                when (response.serviceID) {
                    Constants.ApiServiceId.CARD_API_SERVICE_ID -> {
                        sendResponseToView(
                                responseString = response.message,
                                isSuccess = true,
                                serviceID = response.serviceID,
                                errorCode = response.errorCode,
                                result = response.result
                        )
                    }
                }
            }
        }
    }
    private fun sendResponseToView(
            responseString: String,
            isSuccess: Boolean,
            serviceID: String,
            errorCode: Int = 0,
            result: Any? = null
    ) {
        val responseData = CardResponseWrapper(
                isSuccess,
                responseString,
                errorCode,
                serviceID,
                result

        )
        apiResponse.value = responseData
    }
}