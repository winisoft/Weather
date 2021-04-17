package stevemerollis.codetrial.weather.error.ui

import stevemerollis.codetrial.weather.async.AsyncResult

interface ErrorView {

    companion object {
        fun from(asyncError: AsyncResult.Error): ErrorView {
            return object: ErrorView { }
        }
    }
}