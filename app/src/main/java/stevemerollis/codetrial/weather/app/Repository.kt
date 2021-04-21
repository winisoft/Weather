package stevemerollis.codetrial.weather.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse

interface Repository {

    object Request

    interface Result<T> {
        data class Success<T>(val data: T): Result<T>
        object Error: Result<Nothing>
    }
}