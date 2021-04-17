package stevemerollis.codetrial.weather.currently.app

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.network.helper.NetworkResult


interface CurrentlyRepository {

    suspend fun getCurrentWeather(appId: String, uom: UnitsOfMeasure): Flow<NetworkResult<CurrentlyResponse>>

    suspend fun refresh()
}