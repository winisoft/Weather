package stevemerollis.codetrial.weather.fragment

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel

@OptIn(ExperimentalCoroutinesApi::class)
interface UI {

    val viewModel: WeatherViewModel

    fun render(view: ViewBinding, state: WeatherViewModel.State)

}