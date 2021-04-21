package stevemerollis.codetrial.weather.currently.app

import dispatch.core.DispatcherProvider
import dispatch.core.IOCoroutineScope
import dispatch.core.withIO
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.app.Repository
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.network.call.NetworkResult
import stevemerollis.codetrial.weather.util.lo.logD
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

    override suspend operator fun invoke(
        scope: CoroutineScope,
        appId: String,
        uom: UnitsOfMeasure
    ): Job = scope.launch {
        networkHelper
            .getCurrentWeather()
            .map(::transform)
            .onEach { resultFlow.transform { emit(it) } }
            .collect()
    }

    override val resultFlow: Flow<Repository.Result<CurrentlyResponse>>
        get() = emptyFlow()

    private fun transform(networkResult: NetworkResult<CurrentlyResponse>): Repository.Result<CurrentlyResponse> {
        networkResult as NetworkResult.Success
        return Repository.Result.Success(networkResult.response.body() as CurrentlyResponse)
    }

    interface Result<T> {
        data class Success<D>(val model: D): Result<D>
        data class Error(val title: Int, val message: Int): Result<Nothing>
    }

    companion object {
        val TAG: String = CurrentlyRepositoryImpl::class.simpleName.toString()
    }

}