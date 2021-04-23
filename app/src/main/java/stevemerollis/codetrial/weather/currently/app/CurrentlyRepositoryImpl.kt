package stevemerollis.codetrial.weather.currently.app

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.api.model.CurrentlyResponse
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

    override suspend fun getCurrentWeather(appId: String, uom: UnitsOfMeasure)
    : Flow<NetworkResult<CurrentlyResponse>> = networkHelper.getCurrentWeather(appId, uom)

    companion object {
        val TAG: String = CurrentlyRepositoryImpl::class.simpleName.toString()
    }

}