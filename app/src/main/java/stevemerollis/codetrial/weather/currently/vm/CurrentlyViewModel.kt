package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.async.UseResult
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.error.ui.ErrorView
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
): WeatherViewModel() {

    override suspend fun getResult(intention: Intention): State = when (intention) {
        is ViewModelIntention.LoadUI ->
            getCurrentWeather.mutate(GetCurrentWeather.UseCaseIntention.FetchCurrentWeather).single()
        else ->
            getCurrentWeather.mutate(GetCurrentWeather.UseCaseIntention.FetchCurrentWeather).single()
    }.let { useCaseResult ->
        map(useCaseResult)
    }

    override fun <T> map(result: UseCase.Result<T>): State = when (result) {
        is GetCurrentWeather.UseCaseResult.Success<*> ->
            ViewModelState.Content(result.data as CurrentlyLayoutModel)
        else ->
            ViewModelState.Error(object: ErrorView {})
    }

    sealed class ViewModelIntention: Intention {
        object LoadUI: ViewModelIntention()
        object LaunchForecast: ViewModelIntention()
        object GetCurrentlyLayoutModel: ViewModelIntention()
    }

    sealed class ViewModelState: State {
        object Loading: ViewModelState()
        data class Content(val model: CurrentlyLayoutModel): ViewModelState()
        data class Error(val errorView: ErrorView): ViewModelState()
    }

    companion object {
        val TAG: String = CurrentlyViewModel::class.simpleName.toString()
    }
}