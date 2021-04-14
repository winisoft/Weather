package stevemerollis.codetrial.weather.async

sealed class AsyncResult<out T> {

    abstract fun <T: Any> duplicate(): AsyncResult<out T>

    /**
     * Success response with body
     */
    class Success<out T>(val data: T): AsyncResult<T>() {
        override fun <T : Any> duplicate(): AsyncResult<out T> = Error.Generic
        override fun toString(): String = "Success"
    }

    sealed class Error(
            val message: String? = null,
            val throwable: Throwable? = null
    ): AsyncResult<Nothing>() {

        /**
         * An error caused by any failure not provided by the back office, and not a failure of
         * the network transmission
         */
        object Generic: Error() {
            override fun <T : Any> duplicate(): AsyncResult<out T> = Generic
            override fun toString(): String = "Generic Error"
        }

        /**
         * An exception occurred during the call itself -- most likely an IO exception or timeout
         */
        object Technical: Error(){
            override fun <T : Any> duplicate(): AsyncResult<out T> = Technical
            override fun toString(): String = "Technical Error"
        }
    }
}

