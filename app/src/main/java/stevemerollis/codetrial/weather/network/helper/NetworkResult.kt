package stevemerollis.codetrial.weather.network.helper

import retrofit2.Response

sealed class NetworkResult<T> {

    data class Success<T: Any>(
        val data: T,
        val response: Response<T>
    ) : NetworkResult<T>()

    sealed class Error(
        open val response: Response<Nothing>?,
        open val throwable: Throwable?
    ): NetworkResult<Nothing>() {

        object Unavailable: Error(null, null)

        class Api(
            override val response: Response<Nothing>
        ): NetworkResult.Error(response, null) {
            val httpCode: HttpCode = HttpCode.from(response.code())
        }

        class Technical(
            override val throwable: Throwable?
        ): Error(null, throwable)

        class Generic(
            override val response: Response<Nothing>? = null,
            override val throwable: Throwable? = null
        ): Error(response, throwable)

    }
}
