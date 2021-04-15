package stevemerollis.codetrial.weather.fragment

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel

@OptIn(ExperimentalCoroutinesApi::class)
interface UI {

    val vm: WeatherViewModel

    fun <V: ViewBinding, T: Any> V.render(viewProperties: T)

    val intentionFlow: Flow<Intention>

    fun render(state: WeatherViewModel.ViewModelState)

    interface Intention
}