package co.project.sumedhandroiddemo.dashboard.model



import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class CardsResponse(
    @SerializedName("cards")
    var cards: List<Card?>? = listOf()
) : Parcelable {

    @Parcelize
    data class Card(
        @SerializedName("data")
        var `data`: Data? = Data()
    ) : Parcelable {

        @Parcelize
        data class Data(
            @SerializedName("horizontal_cards")
            var horizontalCards: List<HorizontalCard?>? = listOf(),
            @SerializedName("main_post")
            var mainPost: MainPost? = MainPost(),
            @SerializedName("total_matches_count")
            var totalMatchesCount: Int? = 0
        ) : Parcelable {

            @Parcelize
            data class HorizontalCard(
                @SerializedName("assigned_to")
                var assignedTo: AssignedTo? = AssignedTo(),
                @SerializedName("info")
                var info: String? = "",
                @SerializedName("post_uuid")
                var postUuid: String? = "",
                @SerializedName("price")
                var price: Int? = 0,
                @SerializedName("rent_expected")
                var rentExpected: Int? = 0,
                @SerializedName("sub_info")
                var subInfo: List<SubInfo?>? = listOf(),
                @SerializedName("title")
                var title: String? = "",
                @SerializedName("type")
                var type: Type? = Type(),
                @SerializedName("updation_time")
                var updationTime: Int? = 0,
                @SerializedName("uuid")
                var uuid: String? = ""
            ) : Parcelable {

                @Parcelize
                data class AssignedTo(
                    @SerializedName("name")
                    var name: String? = "",
                    @SerializedName("org_name")
                    var orgName: String? = "",
                    @SerializedName("phone_number")
                    var phoneNumber: String? = "",
                    @SerializedName("profile_pic_url")
                    var profilePicUrl: String? = "",
                    @SerializedName("uuid")
                    var uuid: String? = ""
                ) : Parcelable


                @Parcelize
                data class SubInfo(
                    @SerializedName("perfect_match")
                    var perfectMatch: Boolean? = false,
                    @SerializedName("text")
                    var text: String? = ""
                ) : Parcelable


                @Parcelize
                data class Type(
                    @SerializedName("id")
                    var id: String? = "",
                    @SerializedName("name")
                    var name: String? = ""
                ) : Parcelable
            }


            @Parcelize
            data class MainPost(
                @SerializedName("assigned_to")
                var assignedTo: AssignedTo? = AssignedTo(),
                @SerializedName("info")
                var info: String? = "",
                @SerializedName("match_count")
                var matchCount: Int? = 0,
                @SerializedName("max_budget")
                var maxBudget: Int? = 0,
                @SerializedName("max_rent")
                var maxRent: Int? = 0,
                @SerializedName("post_uuid")
                var postUuid: String? = "",
                @SerializedName("sub_info")
                var subInfo: List<SubInfo?>? = listOf(),
                @SerializedName("title")
                var title: String? = "",
                @SerializedName("type")
                var type: Type? = Type(),
                @SerializedName("uuid")
                var uuid: String? = ""
            ) : Parcelable {

                @Parcelize
                data class AssignedTo(
                    @SerializedName("name")
                    var name: String? = "",
                    @SerializedName("org_name")
                    var orgName: String? = "",
                    @SerializedName("phone_number")
                    var phoneNumber: String? = "",
                    @SerializedName("profile_pic_url")
                    var profilePicUrl: String? = "",
                    @SerializedName("uuid")
                    var uuid: String? = ""
                ) : Parcelable


                @Parcelize
                data class SubInfo(
                    @SerializedName("text")
                    var text: String? = ""
                ) : Parcelable


                @Parcelize
                data class Type(
                    @SerializedName("id")
                    var id: String? = "",
                    @SerializedName("name")
                    var name: String? = ""
                ) : Parcelable
            }
        }
    }
}