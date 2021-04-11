package stevemerollis.codetrial.weather.network.adapter.call

import stevemerollis.codetrial.weather.async.AsyncResult
import okhttp3.Request
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stevemerollis.codetrial.weather.util.lo.logE
import stevemerollis.codetrial.weather.util.lo.logW


internal class NetworkCall<S : Any>(
        private val delegate: Call<S>
) : Call<AsyncResult<S>> {

    override fun enqueue(callback: Callback<AsyncResult<S>>) =
        delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body: S? = response.body()
                val result: AsyncResult<S> = when {
                    response.isSuccessful && body != null -> { AsyncResult.Success(body) }
                    else -> {
                        logW {
                            "${response.raw().request.url} \ncompleted successfully, but returned OnStar error" +
                            "\n\tHttp Code: ${response.code()}: ${response.message()}"
                        }
                        AsyncResult.Error.Generic
                    }
                }

                callback.onResponse(
                    this@NetworkCall,
                    Response.success(result, response.run {
                        okhttp3.Response.Builder()
                            .code(code())
                            .message(message())
                            .protocol(raw().protocol)
                            .request(raw().request)
                            .build()
                    })
                )
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                logE (throwable) {
                    throwable.message ?: "retrofit call failed; not a bad response code -- like, broke-broke."
                }
                callback.onResponse(
                    this@NetworkCall,
                    Response.success(
                        if (throwable is IOException) AsyncResult.Error.Technical
                        else AsyncResult.Error.Generic
                    )
                )
            }
        })

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<AsyncResult<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}