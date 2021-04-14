package stevemerollis.codetrial.weather.currently.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.fragment.FragmentKey
import stevemerollis.codetrial.weather.fragment.UI
import stevemerollis.codetrial.weather.util.lo.logD
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import xkotlin.coroutines.flow.flatMapFirst
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

    suspend fun processIntent(intent: UI.Event) = _intentChannel.send(intent)

    override val value: UI.State = _state.value
        suspend fun get() = withContext(viewModelScope.coroutineContext) {
            _intentChannel.asFlow()
            .transform {
                emit(UI.State.Loading)
                emit(it)
            }.filterIsInstance<CurrentlyFragment.Events.Initial>()
            .sendSingleEvent()
            .map {
                when (it) {
                    is Model.Success -> UI.State.Display(it.model)
                    is Model.Error -> UI.State.Display(it.model)
                }
            }.onEach {
                state = it
            }
            .catch { }
            .stateIn(viewModelScope)
        }

    private fun Flow<UI.Event>.sendSingleEvent()
    : Flow<Model<CurrentlyViewProperties>> = getCurrentWeather()

    private fun <T : UI.Event> Flow<T>.logIntent() = onEach {
        logD { "$TAG: Intent: $it" }
    }

    companion object {
        val TAG: String = CurrentlyViewModel::class.simpleName.toString()
    }
}