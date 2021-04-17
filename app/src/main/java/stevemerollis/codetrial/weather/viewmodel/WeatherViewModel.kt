@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.viewmodel

import dagger.hilt.android.scopes.ViewModelScoped
import dispatch.android.viewmodel.DispatchViewModel
import dispatch.core.DefaultCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.fragment.UI

@ExperimentalCoroutinesApi
@ViewModelScoped
abstract class WeatherViewModel
: DispatchViewModel() {

    fun mutate(intention: Intention): StateFlow<State> {
        return intentChannel.apply { offer(intention) }.run { stateFlow }
    }

    val intentChannel: Channel<Intention> = Channel(Channel.UNLIMITED)

    val stateFlow: StateFlow<State> = MutableStateFlow(State.Init)
        suspend fun get() = intentChannel
            .consumeAsFlow()
            .transform<Intention, State> {
                emit(getResult(it))
            }.onStart {
                emit(State.Loading)
            }.stateIn(viewModelScope)

    abstract suspend fun getResult(intention: Intention): State

    abstract fun <T> map(result: UseCase.Result<T>): State

    interface Intention

    interface State {
        object Init: State
        object Loading: State
    }

    init {
        viewModelScope.launch {
            stateFlow::value.get()
        }
    }
}