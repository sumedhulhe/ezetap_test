package co.project.rewards.baseclasses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import co.project.sumedhandroiddemo.networkmodule.network.APIinterface
import co.project.sumedhandroiddemo.baseclasses.ErrorWrapper
import co.project.sumedhandroiddemo.baseclasses.ResponseWrapper
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

abstract class BaseRepository {

    @Inject
    lateinit var apIinterface: APIinterface
    open lateinit var apiResponse: MutableLiveData<ResponseWrapper>
    open val _errorResponse = MutableLiveData<co.project.sumedhandroiddemo.baseclasses.Error>()

    fun networkCall(
            apiMethod: Int,
            url: String,
            requestObj: Any?,
            serviceID: String,
            resultClass: Class<*>,
            queryParam: HashMap<String, Any> = hashMapOf()
    ) {
        APIinterface.callBack(
                apIinterface,
                apiMethod,
                url,
                requestObj,
                queryParam
        )
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<Any> {
                    override fun onComplete() {
                        //empty implementation
                    }

                    override fun onSubscribe(d: Disposable) {
                        //empty implementation
                    }

                    override fun onError(error: Throwable) {
                        if (error is HttpException) {
                            val errorResponse: ResponseBody =
                                    error.response()?.errorBody()!!
                            var errors: ErrorWrapper? = null
                            try {
                                errors = Gson().fromJson(
                                        errorResponse.string(),
                                        ErrorWrapper::class.java
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            errors?.error?.errorType = error
                            errors?.error?.serviceID = serviceID
                            if (errors?.error?.status_code == 401 || error.code() == 401) {
                                if (errors != null)
                                    _errorResponse.value = errors.error
                                else
                                    _errorResponse.value = co.project.sumedhandroiddemo.baseclasses.Error(serviceID, error.code(), error.code(), "Session expired", "Session expired", error)

                            } else
                                handleResponse(
                                        ResponseWrapper(
                                                error = errors?.error?.message ?: "",
                                                result = null,
                                                serviceID = serviceID,
                                                message = "",
                                                statusCode = errors?.error?.status_code
                                        )
                                )
                        } else {
                            val errors = co.project.sumedhandroiddemo.baseclasses.Error(status_code = 404, serviceID = serviceID, errorType = error, message = "error", developer_message = "error", code = 404)
                            _errorResponse.value = errors
                            handleError(error)
                        }
                    }

                    override fun onNext(response: Any) {
                        if (response != null) {
                            val result = getResultObj(response, resultClass)
                            handleResponse(
                                    ResponseWrapper(
                                            error = "",
                                            result = result,
                                            serviceID = serviceID,
                                            message = "Success",
                                            statusCode = 200
                                    )
                            )
                        } else {
                            handleResponse(
                                    ResponseWrapper(
                                            error = "Something went worng!!",
                                            result = null,
                                            serviceID = serviceID,
                                            message = ""
                                    )
                            )
                        }
                    }
                })
    }


    private fun getResultObj(result: Any, resultClass: Class<*>): Any {
        val gson = Gson()
        Log.e("CLASS NAME::", "" + resultClass.canonicalName)
        val str = gson.toJson(result, result.javaClass)
        if (str.startsWith("[") && str.endsWith("]")) {
            val jsonArray = JSONArray(str)
            val resultObj: Any = gson.fromJson(jsonArray.toString(), resultClass)
            return resultObj
        } else {
            val jsonObject = JSONObject(str)
            val resultObj: Any = gson.fromJson(jsonObject.toString(), resultClass)
            return resultObj
        }
    }

    abstract fun handleResponse(responseObj: ResponseWrapper)

    abstract fun handleError(error: Throwable)
}