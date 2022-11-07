package co.project.sumedhandroiddemo.networkmodule.network



import co.project.sumedhandroiddemo.networkmodule.utils.Constants.ApiMethod.Companion.DELETE_METHOD
import co.project.sumedhandroiddemo.networkmodule.utils.Constants.ApiMethod.Companion.GET_METHOD
import co.project.sumedhandroiddemo.networkmodule.utils.Constants.ApiMethod.Companion.POST_METHOD
import co.project.sumedhandroiddemo.networkmodule.utils.Constants.ApiMethod.Companion.PUT_METHOD

interface APIinterface {

    @retrofit2.http.POST("{url}")
    fun postMethod(
            @retrofit2.http.Path(
                    value = "url",
                    encoded = true
            ) methodUrl: String,
            @retrofit2.http.Body requestObj: Any?,
            @retrofit2.http.QueryMap queryMap: HashMap<String, Any>
    ): io.reactivex.Observable<Any>

    @retrofit2.http.GET
    fun getMethod(
            @retrofit2.http.Url methodUrl: String,
            @retrofit2.http.QueryMap queryMap: HashMap<String, Any>
    ): io.reactivex.Observable<Any>

    @retrofit2.http.PUT("{url}")
    fun putMethod(
            @retrofit2.http.Path(
                    value = "url",
                    encoded = true
            ) methodUrl: String,
            @retrofit2.http.Body requestObj: Any?,
            @retrofit2.http.QueryMap queryMap: HashMap<String, Any>
    ): io.reactivex.Observable<Any>

    @retrofit2.http.DELETE
    fun deleteMethod(
            @retrofit2.http.Url methodUrl: String,
            @retrofit2.http.QueryMap queryMap: HashMap<String, Any>
    ): io.reactivex.Observable<Any>


    companion object {
        fun callBack(
                apiCallInterface: APIinterface?,
                apiMethod: Int,
                url: String,
                requestObj: Any?,
                queryParam: HashMap<String, Any>
        ): io.reactivex.Observable<Any>? {
            var call: io.reactivex.Observable<Any>? = null
            when (apiMethod) {
                GET_METHOD -> call = apiCallInterface?.getMethod(url, queryParam)
                POST_METHOD -> call = apiCallInterface?.postMethod(url, requestObj, queryParam)
                PUT_METHOD -> call = apiCallInterface?.putMethod(url, requestObj, queryParam)
                DELETE_METHOD -> call = apiCallInterface?.deleteMethod(url, queryParam)
            }
            return call
        }
    }

}