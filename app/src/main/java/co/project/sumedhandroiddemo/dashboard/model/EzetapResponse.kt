package co.project.sumedhandroiddemo.dashboard.model



import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class EzetapResponse(
    @SerializedName("heading-text")
    var headingText: String? = "",
    @SerializedName("logo-url")
    var logoUrl: String? = "",
    @SerializedName("uidata")
    var uidata: List<Uidata?>? = listOf()
) : Parcelable {

    @Parcelize
    data class Uidata(
        @SerializedName("hint")
        var hint: String? = "",
        @SerializedName("key")
        var key: String? = "",
        @SerializedName("uitype")
        var uitype: String? = "",
        @SerializedName("value")
        var value: String? = ""
    ) : Parcelable
}