package stevemerollis.codetrial.weather.currently.app

import kotlinx.coroutines.flow.Flow


interface CurrentlyRepository {

    fun getCurrentWeather(): Flow<CurrentlyResponse>

    suspend fun refresh()
}