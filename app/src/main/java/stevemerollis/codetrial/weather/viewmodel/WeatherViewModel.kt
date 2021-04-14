@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import stevemerollis.codetrial.weather.async.ViewModelState
import stevemerollis.codetrial.weather.fragment.UI
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@ViewModelScoped
abstract class WeatherViewModel
constructor(
    protected val _state: MutableStateFlow<UI.State> = MutableStateFlow(UI.State.Init)
): ViewModel(), StateFlow<UI.State> by _state {

    var state: UI.State
        get() = this.value
        set(value) { _state.value = value }

    protected val _intentChannel = BroadcastChannel<UI.Event>(capacity = Channel.BUFFERED)

    fun eventChannel() = BroadcastChannel<UI.Event>(capacity = CONFLATED)


}