package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.fragment.UI
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CurrentlyViewModel
@Inject
constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val refreshCurrentWeather: RefreshCurrentWeather
): WeatherViewModel() {

    override suspend fun Flow<UI.Intention>.onIntentionReceived(): StateFlow<State> = flow {
        emit(State.Loading)
        map {
            when (it) {
                is CurrentlyFragment.Intentions.Retry -> receivedRetry()
                else -> receivedLaunchForecast()
            }
        }
    }.stateIn(viewModelScope)

    sealed class State: ViewModelState {
        object Init: State()
        object Loading: State()
        data class Display<M>(val model: M): State()
    }

    private suspend fun receivedRetry() = getCurrentWeather()
        .map {
            when (it) {
                is Model.Success -> State.Display(it.viewProperties)
                is Model.Error -> State.Display(it.model as Model.Error)
            }
        }.single()

    private fun receivedLaunchForecast() {

    }

    companion object {
        val TAG: String = CurrentlyViewModel::class.simpleName.toString()
    }
}