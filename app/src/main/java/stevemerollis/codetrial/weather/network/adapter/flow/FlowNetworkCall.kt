package stevemerollis.codetrial.weather.network.adapter.flow

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl.logC
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import okhttp3.logText
import okio.IOException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

/**
 * To convert retrofit response to Flow<Resource<T>>.
 * Inspired from FlowCallAdapterFactory
 */
@ExperimentalCoroutinesApi
class FlowNetworkCall<R>(
    private val responseType: Type
) : CallAdapter<R, Flow<AsyncResult<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): Flow<AsyncResult<R>> = flow<AsyncResult<R>> {
        call.awaitResponse().let {
            when {
                it.isSuccessful && it.body() != null -> AsyncResult.Success(this)
                it.isSuccessful -> {
                    logC {
                        "call to ${it.raw().request.url.host} succeeded, but its result was a failure." +
                                "\n\tHttp Code: ${it.code()}\n\tMessage:${it.message()}"
                    }
                    emit(AsyncResult.Error.Generic)
                }
                else -> {
                    logC { "call to ${it.raw().request.url.host} failed \n\tHttp Code: ${it.code()}\n\tMessage:${it.message()}" }
                    emit(AsyncResult.Error.Generic)
                }
            }
        }
    }.onStart {
        logC {
            call.request().logText
        }
    }.catch { cause ->
        if (cause is IOException) {
            logC(cause) { "call failed... for technical reasons" }
            emit(AsyncResult.Error.Technical)
        } else {
            logC(cause) { "call failed... for technical reasons" }
            emit(AsyncResult.Error.Generic)
        }
    }
}