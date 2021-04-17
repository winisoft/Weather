package stevemerollis.codetrial.weather.async

import stevemerollis.codetrial.weather.error.ui.ErrorView

sealed class UseResult<out T> {

    class Success<out T>(val model: T): UseResult<T>() {
        override fun toString(): String = "Success"
    }

    sealed class Error(val model: ErrorView): UseResult<Nothing>(), ErrorView {

        object Generic: Error(object: ErrorView {

        })

        /**
         * An exception that occurred (hopefully) during a network call or some other process that
         * is fundamentally out of our hands -- most likely an IO exception or timeout
         */
        object Technical: Error(object: ErrorView {

        })

        object InvalidArgs: Error(object: ErrorView {

        })

    }
}