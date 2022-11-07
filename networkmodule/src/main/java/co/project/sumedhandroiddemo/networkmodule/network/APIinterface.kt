package co.project.rewards.network



import co.project.sumedhandroiddemo.utils.Constants.ApiMethod.Companion.DELETE_METHOD
import co.project.sumedhandroiddemo.utils.Constants.ApiMethod.Companion.GET_METHOD
import co.project.sumedhandroiddemo.utils.Constants.ApiMethod.Companion.POST_METHOD
import co.project.sumedhandroiddemo.utils.Constants.ApiMethod.Companion.PUT_METHOD
import io.reactivex.Observable
import retrofit2.http.*

interface APIinterface {

    @POST("{url}")
    fun postMethod(
            @Path(
                    value = "url",
                    encoded = true
            ) methodUrl: String,
            @Body requestObj: Any?,
            @QueryMap queryMap: HashMap<String, Any>
    ): Observable<Any>

    @GET
    fun getMethod(
            @Url methodUrl: String,
            @QueryMap queryMap: HashMap<String, Any>
    ): Observable<Any>

    @PUT("{url}")
    fun putMethod(
            @Path(
                    value = "url",
                    encoded = true
            ) methodUrl: String,
            @Body requestObj: Any?,
            @QueryMap queryMap: HashMap<String, Any>
    ): Observable<Any>

    @DELETE
    fun deleteMethod(
            @Url methodUrl: String,
            @QueryMap queryMap: HashMap<String, Any>
    ): Observable<Any>


    companion object {
        fun callBack(
                apiCallInterface: APIinterface?,
                apiMethod: Int,
                url: String,
                requestObj: Any?,
                queryParam: HashMap<String, Any>
        ): Observable<Any>? {
            var call: Observable<Any>? = null
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