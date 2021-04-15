package stevemerollis.codetrial.weather.currently.frag

import androidx.fragment.app.viewBinding
import androidx.viewbinding.ViewBinding
import com.github.matteobattilana.weather.WeatherView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewProperties
import stevemerollis.codetrial.weather.databinding.FragmentCurrentlyBinding
import stevemerollis.codetrial.weather.fragment.UI
import stevemerollis.codetrial.weather.fragment.WeatherFragment
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@ActivityScoped
@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
class CurrentlyFragment
@Inject
constructor(
    val weatherView: WeatherView
) : WeatherFragment<CurrentlyViewModel, CurrentlyFragment.Intentions>
(R.layout.fragment_currently) {

    val viewBinding: FragmentCurrentlyBinding? by viewBinding(FragmentCurrentlyBinding::bind)

    override val intentionFlow: Flow<UI.Intention> =
        merge(flowOf(Intentions.Retry))
        .onEach { vm.intentChannel.offer(it) }

    sealed class Intentions: UI.Intention {
        object Retry: Intentions()
        object LaunchForecast: Intentions()
    }

    override fun render(state: WeatherViewModel.ViewModelState) {
        when (state) {
            is Model.Success<*>
                -> viewBinding?.apply { render(state.viewProperties as CurrentlyViewProperties) }
        }
    }

    override fun <V : ViewBinding, T : Any> V.render(viewProperties: T) {
        //apply to layout
    }

}