package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.app.Repository
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.currently.frag.CurrentlyViewEvent
import stevemerollis.codetrial.weather.currently.frag.UI
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.error.ui.ErrorView
import stevemerollis.codetrial.weather.intention.Intention
import stevemerollis.codetrial.weather.modelstore.ModelSubscriber
import stevemerollis.codetrial.weather.util.lo.logD
import stevemerollis.codetrial.weather.viewmodel.CurrentlyState
import stevemerollis.codetrial.weather.viewmodel.State
import stevemerollis.codetrial.weather.viewmodel.UseCase
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CurrentlyViewModel
@Inject
constructor(
    private val getCurrentWeather: GetCurrentWeather
): WeatherViewModel<CurrentlyLayoutModel>() {

    override val stateFlow: StateFlow<CurrentlyLayoutModel>
        get() = _stateFlow

    override fun UseCase.Result<*>.map(): CurrentlyLayoutModel = when (this) {
        is UseCase.Result.Success<*> -> {
            this.model as CurrentlyLayoutModel
        }
        else -> CurrentlyLayoutModel.INITIAL // TODO: launch error fragment
    }

    sealed class Intentions: Intention<CurrentlyLayoutModel> {
        object LoadUI: Intentions() {
            override fun reduce(previous: CurrentlyLayoutModel): CurrentlyLayoutModel =
                previous.copy()
        }

        object Refresh: Intentions() {
            override fun reduce(previous: CurrentlyLayoutModel): CurrentlyLayoutModel =
                previous.copy()
        }
    }

    sealed class CurrentlyState: State {
        object Loading: CurrentlyState()
        data class Content(val model: CurrentlyLayoutModel): CurrentlyState()
        data class Error(val errorView: ErrorView): CurrentlyState()
    }

    companion object {
        val TAG: String = CurrentlyViewModel::class.simpleName.toString()
    }

    override suspend fun StateFlow<CurrentlyLayoutModel>.subscribeToModel() {
        viewModelScope.launch {

        }
    }
}