package co.project.sumedhandroiddemo.networkmodule.utils

interface Constants {
    interface ApiMethod {
        companion object {
            const val GET_METHOD = 0
            const val POST_METHOD = 1
            const val PUT_METHOD = 2
            const val DELETE_METHOD = 3
            const val INTENT_DATA_KEY="INTENT_DATA_KEY"

        }
}
    interface ApiServiceId{
        companion object {
            const val CARD_API_SERVICE_ID="CARD_API_SERVICE_ID"
        }
    }
    interface ApiUrl{
        companion object{
            const val CARD_API_URL="mobileapps/android_assignment.json"
        }
    }
}