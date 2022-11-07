package co.project.sumedhandroiddemo.repository

import androidx.lifecycle.MutableLiveData
import co.project.rewards.baseclasses.BaseRepository
import co.project.sumedhandroiddemo.baseclasses.ResponseWrapper
import co.project.sumedhandroiddemo.dashboard.model.EzetapResponse
import co.project.sumedhandroiddemo.networkmodule.network.APIinterface
import co.project.sumedhandroiddemo.networkmodule.utils.Constants
import javax.inject.Inject

class DashboardRepository @Inject constructor(val service: APIinterface) : BaseRepository() {

    fun getCardResponse(
            apiResponse: MutableLiveData<ResponseWrapper>,
            serviceID: String,

            ) {
        this.apiResponse = apiResponse

        networkCall(
                Constants.ApiMethod.GET_METHOD,
                Constants.ApiUrl.CARD_API_URL, Any(), serviceID, EzetapResponse::class.java)
    }
    override fun handleResponse(responseObj: ResponseWrapper) {
        apiResponse.value = responseObj
    }

    override fun handleError(error: Throwable) {

    }
}