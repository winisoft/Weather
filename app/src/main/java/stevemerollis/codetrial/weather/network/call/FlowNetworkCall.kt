package stevemerollis.codetrial.weather.network.call

import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl.logC
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type


@ExperimentalCoroutinesApi
class FlowNetworkCall<R>(
    private val responseType: Type
) : CallAdapter<R, Flow<NetworkResult<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): Flow<NetworkResult<R>> = flow<NetworkResult<R>> {
        call.awaitResponse().let {
            when {
                it.isSuccessful && it.body() != null -> emit(NetworkResult.Success(it))
                it.isSuccessful -> emit(NetworkResult.Error.ApiError(it))
                else -> emit(NetworkResult.Error.Generic(it, null))
            }
        }
    }.catch { cause ->
        if (cause is IOException) {
            logC(cause) { "call failed... for technical reasons" }
            emit(NetworkResult.Error.Technical(cause))
        } else {
            logC(cause) { "call failed... for technical reasons" }
            emit(NetworkResult.Error.Generic<Nothing>(null, cause))
        }
    }
}