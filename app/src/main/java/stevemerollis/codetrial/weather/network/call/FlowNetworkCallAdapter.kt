package stevemerollis.codetrial.weather.network.call

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class FlowNetworkCallAdapter<S : Any>(val type: Type) : CallAdapter<S, Flow<NetworkResult<S>>> {

    override fun responseType(): Type = type

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<S>)
            : Flow<NetworkResult<S>> = FlowNetworkCall<S>(call::class.java).adapt(call)
}