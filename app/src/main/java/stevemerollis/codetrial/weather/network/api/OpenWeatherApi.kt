package stevemerollis.codetrial.weather.network.api


interface OpenWeatherApi {



    companion object {

        const val CONTENT_TYPE = "Content-Type"
        const val ACCEPT = "Accept"
        const val TYPE_JSON = "application/json"
        const val TYPE_TEXT = "text/plain"

        const val QUOTES_ENDPOINT = "api/v1/account/vehicles/{vin}/quotes"
        const val USER_INFO_ENDPOINT = "api/v1/account/userInfo"
        const val ORDERS_ENDPOINT = "/api/v1/account/vehicles/{vin}/orders"
        const val PAY_METHODS_ENDPOINT = "api/v1/accounts/billing/paymentMethods"
        const val REQUEST_CODE_ENDPOINT = "/api/otp/v2/otp/{profile}/send"
        const val CONFIRM_CODE_ENDPOINT = "/api/v1/oauth/token"
        const val PASSWORD_GRANT = "/api/v1/oauth/token"
        const val CODE_QUERY_PARAM = "code"

        const val CONTENT_TYPE_JSON = "$CONTENT_TYPE:$TYPE_JSON"
        const val ACCEPT_JSON = "$ACCEPT: $TYPE_JSON"

    }
}
