package stevemerollis.codetrial.weather.network.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse

interface NetworkHelper {

    fun getCurrentWeather(): Flow<NetworkResult<CurrentlyResponse>>

}
