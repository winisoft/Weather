package stevemerollis.codetrial.weather.network.helper

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.api.model.CurrentlyResponse
import stevemerollis.codetrial.weather.network.call.NetworkResult

interface NetworkHelper {

    fun getCurrentWeather(appId: String, uom: UnitsOfMeasure): Flow<NetworkResult<CurrentlyResponse>>

}
