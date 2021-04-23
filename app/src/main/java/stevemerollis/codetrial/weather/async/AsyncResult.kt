package stevemerollis.codetrial.weather.async

/**
 * State representation of the result from some asynchronous process.
 *
 * A repository may return a given data type from one of several potential data sources, for instance,
 * from a network call or from previously successful cached data. These have their own state representations,
 * e.g. [NetworkResult] that typify unique properties of that process; this one is used to say to a
 * use case, "data access is done, here's enough information to figure out what to show from business logic"
 */
sealed class AsyncResult<out T> {

    class Success<out T>(val data: T): AsyncResult<T>() {
        override fun toString(): String = "Success"
    }

    sealed class Error(
            val message: String? = null,
            val throwable: Throwable? = null
    ): AsyncResult<Nothing>() {

        object Generic: Error() {
            override fun toString(): String = "Generic Error"
        }

        object Technical: Error(){
            override fun toString(): String = "Technical Error"
        }
    }
}

