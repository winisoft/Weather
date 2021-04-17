package stevemerollis.codetrial.weather.currently.app

import dispatch.core.withIO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.network.call.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class CurrentlyRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper
) : CurrentlyRepository {

    private suspend fun getCurrentWeatherFromRemote(): Flow<NetworkResult<CurrentlyResponse>> =
        withIO {
            networkHelper.getCurrentWeather()
        }

    override suspend fun getCurrentWeather(appId: String, uom: UnitsOfMeasure): Flow<NetworkResult<CurrentlyResponse>> {

        return getCurrentWeatherFromRemote()

    }

    override suspend fun refresh() {
        getCurrentWeatherFromRemote()
    }

    companion object {
        val TAG: String = CurrentlyRepositoryImpl::class.simpleName.toString()
    }

}