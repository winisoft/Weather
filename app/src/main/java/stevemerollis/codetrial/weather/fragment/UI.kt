package stevemerollis.codetrial.weather.fragment

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.async.AsyncResult

interface UI {

    val userIntentFlow: Flow<Event>

    fun <M> render(model: M)

    sealed class State {
        object Init: State()
        object Loading: State()
        data class Display<M>(val model: M): State()
    }

    interface Event
}