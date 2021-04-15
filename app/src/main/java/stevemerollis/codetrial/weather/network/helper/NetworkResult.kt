package stevemerollis.codetrial.weather.network.helper

import retrofit2.Response

sealed class NetworkResult<out T> {

    data class Success<T>(
        val response: Response<T>
    ) : NetworkResult<T>()

    sealed class Error: NetworkResult<Nothing>() {

        object Unavailable: Error()

        data class ApiError<T>(
            val response: Response<T>
        ): Error() {
            val httpCode: HttpCode = HttpCode.from(response.code())
        }

        data class Technical(
            val throwable: Throwable?
        ): Error()

        data class Generic<T>(
            val response: Response<T>? = null,
            val throwable: Throwable? = null
        ): Error()
    }
}
