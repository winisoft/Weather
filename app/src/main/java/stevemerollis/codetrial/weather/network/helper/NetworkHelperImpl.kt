@file:Suppress("UNCHECKED_CAST")
package stevemerollis.codetrial.weather.network.helper

import dispatch.core.DispatcherProvider
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.network.state.NetStateUtil
import stevemerollis.codetrial.weather.api.OpenWeatherApi
import stevemerollis.codetrial.weather.util.lo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.api.model.CurrentlyResponse
import stevemerollis.codetrial.weather.network.call.NetworkResult
import java.io.IOException
import javax.inject.Inject


class NetworkHelperImpl
@Inject
constructor(
    private val dispatcher: DispatcherProvider,
    private val openWeatherApi: OpenWeatherApi,
    private val netStateUtil: NetStateUtil
) : NetworkHelper {

    suspend fun <T> (() -> T).call(scope: CoroutineScope)
            : Flow<AsyncResult<T>> = withContext(scope.coroutineContext + dispatcher.io) {

        if (netStateUtil.isNetworkAvailable().value.not()) {
            lo.logE { "Network unavailable! return generic error" }
            flowOf(AsyncResult.Error.Generic)
        } else
            flow<AsyncResult<T>> {
                invoke().let {
                    if (it is Deferred<*>) it.await() else it
                }.run {
                    this as T
                    emit(AsyncResult.Success(this))
                }
            }.catch { cause: Throwable? ->
                when (cause) {
                    is IOException -> {
                        lo.logE { "IOException in NetworkCall. Emit technical error to repository." }
                        emit(AsyncResult.Error.Technical as AsyncResult.Error)
                    }
                    else -> {
                        lo.logE(cause) { "Exception during network api call. Emitting generic error. Cause: ${cause?.message}" }
                        emit(AsyncResult.Error.Generic)
                    }
                }
            }
    }

    val bearer: (() -> String) -> String
        get() = { token -> "Bearer ${token()}" }

    fun String.asBearer() = "Bearer $this"

    companion object {
        val TAG: String = NetworkHelperImpl::class.simpleName.toString()
    }

    override fun getCurrentWeather(appId: String, uom: UnitsOfMeasure): Flow<NetworkResult<CurrentlyResponse>> {

        return openWeatherApi.getCurrentWeatherResponse(0.0, 0.0, uom.toString(), appId)
    }
}
