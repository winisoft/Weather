package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.fragment.UI
//import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.util.AppIdUtil
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@ViewModelScoped
class GetCurrentWeather
@Inject
constructor(
    val appIdUtil: AppIdUtil,
    //val prefManager: PreferenceManager,
    val currentlyRepository: CurrentlyRepository
) {

    val appId: String = appIdUtil.getApiToken()


    val intentChannel = Channel<UI.Intention>(capacity = Channel.UNLIMITED)
    protected val _state: MutableStateFlow<WeatherViewModel.ViewModelState> = MutableStateFlow(
        WeatherViewModel.ViewModelState.Init)
    val state: StateFlow<WeatherViewModel.ViewModelState> get() = _state

    operator fun invoke(): Flow<Model<CurrentlyViewProperties>> = flow {

    }
}