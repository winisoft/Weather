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

        /**
         * The offer properties in the intent bundle that launched the payment app are not legal
         */
        object InvalidArgs: Error(){
            override fun <T : Any> duplicate(): AsyncResult<out T> = InvalidArgs
            override fun toString(): String = "Invalid Arguments Error"
        }

        object Ons551: Error(){
            override fun <T : Any> duplicate(): AsyncResult<out T> = Ons551
            override fun toString(): String = "Error ONS-551: Insufficient Funds"
        }

        object Buy009: Error(){
            override fun <T : Any> duplicate(): AsyncResult<out T> = Buy009
            override fun toString(): String = "Error BUY-009: Duplicate Purchase Attempt"
        }

        fun <T: Any> copy(): AsyncResult<out T> = when(this) {
            is InvalidArgs -> InvalidArgs
            is Generic -> Generic
            is Technical -> Technical
            is Ons551 -> Ons551
            is Buy009 -> Buy009
        }

        fun toModel(): Model.Error = when(this) {
            is InvalidArgs -> Model.Error.InvalidArgs
            is Generic -> Model.Error.Generic
            is Technical -> Model.Error.Technical
            is Ons551 -> Model.Error.Ons551
            is Buy009 -> Model.Error.Buy009
        }
    }
}

