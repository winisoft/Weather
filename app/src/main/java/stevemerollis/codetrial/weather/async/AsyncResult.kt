package stevemerollis.codetrial.weather.async

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

