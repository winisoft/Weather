package stevemerollis.codetrial.weather.currently.app

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.network.helper.NetworkResult


interface CurrentlyRepository {

    suspend fun getCurrentWeather(): Flow<NetworkResult<CurrentlyResponse>>

    suspend fun refresh()
}