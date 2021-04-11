package stevemerollis.codetrial.weather.network.adapter.call

import stevemerollis.codetrial.weather.async.AsyncResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type


@Suppress("UNCHECKED_CAST")
class NetworkCallAdapter<S : Any>(val type: Type) : CallAdapter<S, Call<AsyncResult<S>>> {

    override fun responseType(): Type = type

    override fun adapt(call: Call<S>): Call<AsyncResult<S>> {
        return NetworkCall(call)
    }
}