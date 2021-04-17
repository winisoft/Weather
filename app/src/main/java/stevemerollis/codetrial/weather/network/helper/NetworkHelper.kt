package stevemerollis.codetrial.weather.network.helper

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.network.call.NetworkResult

interface NetworkHelper {

    fun getCurrentWeather(): Flow<NetworkResult<CurrentlyResponse>>

}
