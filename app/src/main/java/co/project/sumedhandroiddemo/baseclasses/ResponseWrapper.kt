package co.project.sumedhandroiddemo.baseclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseWrapper(
        @SerializedName("error")
        var error: String,
        @SerializedName("result")
        var result: Any?,
        @SerializedName("serviceID")
        var serviceID: String,
        @SerializedName("errorCode")
        var errorCode: Int = 0,
        @SerializedName("statusCode")
        var statusCode: Int? = 0,
        @SerializedName("message")
        val message: String
)
@Parcelize
data class Error(
        @SerializedName("serviceID")
        var serviceID: String,
        @SerializedName("code")
        val code: Int,
        @SerializedName("status_code")
        val status_code: Int,
        @SerializedName("developer_message")
        val developer_message: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("errorType")
        var errorType: Throwable?
): Parcelable
@Parcelize
data class ErrorWrapper(
        @SerializedName("error")
        val error: Error,
        @SerializedName("success")
        val success: Boolean

): Parcelable


data class CardResponseWrapper(
        @SerializedName("isSuccess")
        var isSuccess: Boolean,
        @SerializedName("message")
        var message: String,
        @SerializedName("error")
        var error: Int,
        @SerializedName("serviceID")
        var serviceID: String,
        var result: Any? = null
)