package stevemerollis.codetrial.weather.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import stevemerollis.codetrial.weather.fragment.UI


@ExperimentalCoroutinesApi
interface AbstractWeatherViewModel {

    val _state: MutableStateFlow<UI.State>

    val state: StateFlow<UI.State>

    fun eventChannel(): BroadcastChannel<UI.Event>


}