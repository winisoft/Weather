package stevemerollis.codetrial.weather.async

import stevemerollis.codetrial.weather.error.ui.ErrorView

sealed class Model<out T> {

    /**
     * Success response with body
     */
    class Success<out T>(val model: T): Model<T>() {
        override fun toString(): String = "Success"
    }

    sealed class Error(val model: ErrorView): Model<Nothing>(), ErrorView {

        /**
         *
         */
        object Generic: Error(object: ErrorView {

        })

        /**
         * An exception that occurred (hopefully) during a network call or some other process that
         * is fundamentally out of our hands -- most likely an IO exception or timeout
         */
        object Technical: Error(object: ErrorView {

        })

        /**
         * The offer properties in the intent bundle that launched the payment app are not legal
         */
        object InvalidArgs: Error(object: ErrorView {

        })

    }
}