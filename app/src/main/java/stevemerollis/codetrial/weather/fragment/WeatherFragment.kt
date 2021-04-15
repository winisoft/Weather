package stevemerollis.codetrial.weather.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel

@ExperimentalCoroutinesApi
abstract class WeatherFragment<V: WeatherViewModel, I: UI.Intention>
constructor(layoutRes: Int)
: Fragment(layoutRes), UI {

    override val vm: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.state
            .onEach { render(it) }
            .launchIn(lifecycleScope)
    }
}