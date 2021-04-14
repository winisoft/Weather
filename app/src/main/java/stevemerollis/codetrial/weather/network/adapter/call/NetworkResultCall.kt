package stevemerollis.codetrial.weather.network.adapter.call

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stevemerollis.codetrial.weather.network.helper.NetworkResult
import java.io.IOException
import java.lang.UnsupportedOperationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NetworkResultCall<S: Any>
@Inject
constructor(
    private val delegate: Call<S>
): Call<NetworkResult<S>> {

    override fun enqueue(callback: Callback<NetworkResult<S>>) {
        return delegate.enqueue(object: Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {

                response.run {
                    when {
                        response.isSuccessful
                        -> NetworkResult.Success(response)
                        errorBody()?.contentLength()?.let { it > 0L } ?: false
                        -> NetworkResult.Error.ApiError(response)
                        else
                        -> NetworkResult.Error.Generic(response, null)
                    }.let {
                        callback.onResponse(this@NetworkResultCall, Response.success(it))
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(if (throwable is IOException)
                        NetworkResult.Error.Technical(throwable)
                    else
                        NetworkResult.Error.Generic<Nothing>(null, throwable)
                    )
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResultCall(delegate.clone())

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun execute(): Response<NetworkResult<S>> {
        throw UnsupportedOperationException("NetworkResultCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = Timeout()
}