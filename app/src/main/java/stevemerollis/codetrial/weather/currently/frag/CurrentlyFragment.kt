package stevemerollis.codetrial.weather.currently.frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.clicks
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.initializations
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dispatch.core.MainCoroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.currently.frag.CurrentlyIntentionFactory.process
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.databinding.FragmentCurrentlyBinding
import stevemerollis.codetrial.weather.fragment.FragmentViewBinder
import stevemerollis.codetrial.weather.fragment.WeatherFragment
import stevemerollis.codetrial.weather.modelstore.ModelSubscriber
import stevemerollis.codetrial.weather.util.lo.logD
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@ActivityScoped
@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
class CurrentlyFragment
@Inject
constructor(

): Fragment(), UI<CurrentlyViewEvent>, ModelSubscriber<CurrentlyLayoutModel> {

    private lateinit var viewBinding: FragmentCurrentlyBinding
    private val viewModel: CurrentlyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentCurrentlyBinding.inflate(inflater)
        lifecycleScope.launchWhenResumed {
            viewModel.stateFlow.subscribeToModel()
        }
        return viewBinding.root
    }

    override fun viewEvents(): Flow<CurrentlyViewEvent> = merge(
        flowOf(CurrentlyViewEvent.Initial)
        //,swipes()
    )

    override suspend fun StateFlow<CurrentlyLayoutModel>.subscribeToModel() {
        collect {
            viewBinding.apply {
                weatherView.angle = it.weatherViewModel.angle
                weatherView.emissionRate = it.weatherViewModel.emissionRate
                weatherView.fadeOutPercent = it.weatherViewModel.fadeOutPercent
                weatherView.precipType = it.weatherViewModel.precipitationType
                weatherView.speed = it.weatherViewModel.speed

//                it.summary.condition
//                it.summary.temperatureString
//                it.summary.thermometerImage
                //and so on
            }
        }
    }


}