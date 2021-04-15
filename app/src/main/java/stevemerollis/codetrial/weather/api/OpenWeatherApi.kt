package stevemerollis.codetrial.weather.api

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


}
