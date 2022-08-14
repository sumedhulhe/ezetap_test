package co.project.sumedhandroiddemo.repository

import androidx.lifecycle.MutableLiveData
import co.project.rewards.baseclasses.BaseRepository
import co.project.rewards.network.APIinterface
import co.project.sumedhandroiddemo.baseclasses.ResponseWrapper
import co.project.sumedhandroiddemo.dashboard.model.CardsResponse
import co.project.sumedhandroiddemo.utils.Constants
import javax.inject.Inject

class DashboardRepository @Inject constructor(val service: APIinterface) : BaseRepository() {

    fun getCardResponse(
            apiResponse: MutableLiveData<ResponseWrapper>,
            serviceID: String,

            ) {
        this.apiResponse = apiResponse

        networkCall(
                Constants.ApiMethod.GET_METHOD,
                Constants.ApiUrl.CARD_API_URL, Any(), serviceID, CardsResponse::class.java)
    }
    override fun handleResponse(responseObj: ResponseWrapper) {
        apiResponse.value = responseObj
    }

    override fun handleError(error: Throwable) {

    }
}