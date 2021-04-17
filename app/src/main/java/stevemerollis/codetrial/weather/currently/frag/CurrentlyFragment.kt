package stevemerollis.codetrial.weather.currently.frag

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewBinding
import androidx.viewbinding.ViewBinding
import com.github.matteobattilana.weather.WeatherView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.UseResult
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
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
constructor()
: WeatherFragment
<FragmentCurrentlyBinding>
(FragmentCurrentlyBinding::bind) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.intentChannel.offer(CurrentlyViewModel.ViewModelIntention.LoadUI)
    }

    override fun render(view: ViewBinding, state: WeatherViewModel.State) {
        when (state) {
            is CurrentlyViewModel.ViewModelState.Content
                -> viewBinding?.apply { showContent(state.model) }
        }
    }

    private fun FragmentCurrentlyBinding.showContent(model: CurrentlyLayoutModel) {
        weatherView.emissionRate = if (model.condition.isNotEmpty()) 1.0f else 0.0f
    }
}