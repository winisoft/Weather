package stevemerollis.codetrial.weather.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
abstract class WeatherFragment<M: Any, V: StateFlow<UI.State>, I: UI.Event>
constructor(layoutRes: Int)
: Fragment(layoutRes), UI {

    val vm: V by viewModels()

    abstract override fun <M> render(model: M)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            vm.collect {
                render(it)
            }
        }
    }
}