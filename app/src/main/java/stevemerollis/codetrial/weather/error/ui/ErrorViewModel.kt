package stevemerollis.codetrial.weather.error.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.intention.Intention
import stevemerollis.codetrial.weather.viewmodel.State
import stevemerollis.codetrial.weather.viewmodel.UseCase
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel


@OptIn(ExperimentalCoroutinesApi::class)
class ErrorViewModel() : WeatherViewModel<State>() {

    override val stateFlow: StateFlow<State> = _stateFlow

    override fun UseCase.Result<*>.map(): State {
        TODO("Not yet implemented")
    }

    suspend fun get() = flow<State> {
//        val e = ErrorState.Error(model = UseCase.Result.Success("") as UseCase.Result)
//        emit(WeatherViewModel.State.Init) // TODO: got a real mess here.
    }.stateIn(viewModelScope)

    fun informOfIntentions(intention: Intention<State>) {

    }

    sealed class ErrorState: State {
        data class Error(val model: ErrorView): ErrorState()
    }

    override suspend fun StateFlow<State>.subscribeToModel() {
        TODO("Not yet implemented")
    }
}