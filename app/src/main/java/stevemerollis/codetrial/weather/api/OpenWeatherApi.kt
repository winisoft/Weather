package stevemerollis.codetrial.weather.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import stevemerollis.codetrial.weather.api.model.CurrentlyResponse
import stevemerollis.codetrial.weather.forecast.ForecastResponse
import stevemerollis.codetrial.weather.network.call.NetworkResult


interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeatherResponse(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("units") units : String,
        @Query("APPID") appId : String
    ) : Flow<NetworkResult<CurrentlyResponse>>

    @GET("forecast")
    fun getForecastWeatherResponse(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("units") units : String = "imperial",
        @Query("APPID") appId : String
    ) : Flow<NetworkResult<ForecastResponse>>


}
