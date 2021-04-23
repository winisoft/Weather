package stevemerollis.codetrial.weather.currently.frag

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.databinding.FragmentCurrentlyBinding
import stevemerollis.codetrial.weather.location.ILocationHelper
import stevemerollis.codetrial.weather.main.MainActivity
import stevemerollis.codetrial.weather.modelstore.ModelSubscriber
import stevemerollis.codetrial.weather.permissions.PermissionsUtil
import stevemerollis.codetrial.weather.permissions.PermissionsUtil.Companion.CODE_ACCESS_LOCATION
import javax.inject.Inject

@ActivityScoped
@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
class CurrentlyFragment
@Inject
constructor(
    val locationHelper: ILocationHelper
): Fragment(), ModelSubscriber<CurrentlyViewModel.State> {

    private lateinit var viewBinding: FragmentCurrentlyBinding
    private val viewModel: CurrentlyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentCurrentlyBinding.inflate(inflater)
        requestLocation()
        lifecycleScope.launchWhenResumed {
            viewModel.resultFlow.subscribeToModel()
        }

        return viewBinding.root
    }

    private fun requestLocation() {
        PermissionsUtil().requestPermission(activity as Activity, ACCESS_FINE_LOCATION, CODE_ACCESS_LOCATION) { granted ->
            if (granted) {
                locationHelper.getLastLocation(activity as Activity).addOnSuccessListener { success ->
                    lifecycleScope.launch {
                        viewModel.process(CurrentlyViewEvent.Initial(success!!))
                    }
                }.addOnFailureListener {
                    lifecycleScope.launch {
                        viewModel.process(CurrentlyViewEvent.Initial(null))
                    }
                }
            }
            else {
                lifecycleScope.launch {
                    viewModel.process(CurrentlyViewEvent.Initial(null))
                }
            }
        }
    }

    override suspend fun StateFlow<CurrentlyViewModel.State>.subscribeToModel() {
        collect {
            when (it) {
                is CurrentlyViewModel.State.Content -> {
                    val result = it.value
                    viewBinding.apply {
                        weatherView.angle = result.weatherViewModel.angle
                        weatherView.emissionRate = result.weatherViewModel.emissionRate
                        weatherView.fadeOutPercent = result.weatherViewModel.fadeOutPercent
                        weatherView.precipType = result.weatherViewModel.precipitationType
                        weatherView.speed = result.weatherViewModel.speed
                    }
                }
            }

        }
    }


}