package stevemerollis.codetrial.weather.network.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.forecast.ForecastResponse
import stevemerollis.codetrial.weather.network.helper.NetworkResult


interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeatherResponse(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        //TODO: this should really be configurable in a settings activity or something
        @Query("units") units : String = "imperial",
        @Query("APPID") appId : String
    ) : Flow<NetworkResult<CurrentlyResponse>>

    @GET("forecast")
    fun getForecastWeatherResponse(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("units") units : String = "imperial",
        @Query("APPID") appId : String
    ) : Flow<NetworkResult<ForecastResponse>>

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
