@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.viewmodel

import dagger.hilt.android.scopes.ViewModelScoped
import dispatch.android.viewmodel.DispatchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.fragment.UI

@ExperimentalCoroutinesApi
@ViewModelScoped
abstract class WeatherViewModel
: DispatchViewModel() {

    val intentChannel = Channel<UI.Intention>(capacity = Channel.UNLIMITED)
    protected val _state: MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState.Init)
    val state: StateFlow<ViewModelState> get() = _state

    abstract suspend fun Flow<UI.Intention>.onIntentionReceived(): StateFlow<ViewModelState>

    interface ViewModelState {
        object Init: ViewModelState
    }

    init {
        viewModelScope.launch {
            intentChannel
                .consumeAsFlow()
                .onIntentionReceived()
        }
    }
}