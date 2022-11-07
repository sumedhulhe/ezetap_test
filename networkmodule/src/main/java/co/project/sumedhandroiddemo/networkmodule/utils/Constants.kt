package co.project.sumedhandroiddemo.utils

interface Constants {
    interface ApiMethod {
        companion object {
            const val GET_METHOD = 0
            const val POST_METHOD = 1
            const val PUT_METHOD = 2
            const val DELETE_METHOD = 3

        }
}
    interface ApiServiceId{
        companion object {
            const val CARD_API_SERVICE_ID="CARD_API_SERVICE_ID"
        }
    }
    interface ApiUrl{
        companion object{
            const val CARD_API_URL="v3/c52cf4ce-a639-42d7-a606-2c0a8b848536"
        }
    }
}