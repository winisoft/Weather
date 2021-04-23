package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import dispatch.android.viewmodel.DispatchViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.error.ui.ErrorView
import stevemerollis.codetrial.weather.currently.frag.CurrentlyViewEvent
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.intention.Intention
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CurrentlyViewModel
@Inject
constructor(
    private val getCurrentWeather: GetCurrentWeather
): DispatchViewModel() {

    private val intentionChannel = Channel<Intention<State>>()
    private val _resultFlow: MutableStateFlow<State> = MutableStateFlow(State.Init)
    val resultFlow: StateFlow<State> get() = _resultFlow

    sealed class State {
        object Init: State()
        object Loading: State()
        data class Content(val value: CurrentlyLayoutModel): State()
        data class Navigate(val value: ErrorView): State()
    }

    sealed class Intentions: Intention<State> {

        data class Load(
            val scope: CoroutineScope,
            val getCurrentWeather: GetCurrentWeather,
            val _resultFlow: MutableStateFlow<State>
        ): Intentions() {
            override suspend fun execute() {
                getCurrentWeather().let {
                    when (it) {
                        is GetCurrentWeather.State.Result -> _resultFlow.value = State.Content(it.value)
                        is GetCurrentWeather.State.Error -> _resultFlow.value = State.Navigate(it.value)
                    }
                }
            }
        }
    }

    suspend fun process(viewEvent: CurrentlyViewEvent) = when (viewEvent) {
            is CurrentlyViewEvent.Initial ->
                Intentions.Load(viewModelScope, getCurrentWeather, _resultFlow)
            else ->
                Intentions.Load(viewModelScope, getCurrentWeather, _resultFlow)
        }.let {
            intentionChannel.offer(it)
        }

    init {
        viewModelScope.launch {
            while (isActive)
                intentionChannel.consumeAsFlow()
                    .onEach { it.execute() }
        }
    }

    companion object {
        val TAG: String = CurrentlyViewModel::class.simpleName.toString()
    }
}