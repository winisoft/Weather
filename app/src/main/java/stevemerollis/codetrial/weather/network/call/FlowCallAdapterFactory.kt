package stevemerollis.codetrial.weather.network.call

import kotlinx.coroutines.flow.Flow
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory
private constructor()
    : CallAdapter.Factory() {

    companion object {
        fun create() = FlowCallAdapterFactory()
    }

    override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Flow::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>"
        }
        val responseType = getParameterUpperBound(0, returnType)

        check(responseType is ParameterizedType && getRawType(responseType) == Response::class.java) {
            "Response must be parameterized as Response<Foo> or Response<out Foo>"
        }
        return FlowNetworkCallAdapter<Any>(getParameterUpperBound(0, responseType))
    }
}