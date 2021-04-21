package stevemerollis.codetrial.weather.error.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.ViewModelState
import stevemerollis.codetrial.weather.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.fragment.WeatherFragment
import stevemerollis.codetrial.weather.util.lo.logI
import stevemerollis.codetrial.weather.viewmodel.State
import stevemerollis.codetrial.weather.viewmodel.UseCase
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel


@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class ErrorFragment(override val viewBinding: FragmentErrorBinding?)
: WeatherFragment<FragmentErrorBinding>
(FragmentErrorBinding::bind) {

    val viewModel: ErrorViewModel by viewModels()
    override fun viewEvents(): Flow<ViewEvent> {
        TODO("Not yet implemented")
    }

    fun FragmentErrorBinding.render(state: State): FragmentErrorBinding {
        TODO("Not yet implemented")
    }

//    override fun FragmentErrorBinding.render(state: WeatherViewModel) = apply {
//        val error: WeatherViewModel.State.Error = state as WeatherViewModel.State.Error
//        tvErrorTitle.text = getString(error.rTitle)
//        tvErrorMessage.text = getString(error.rMessage)
//        btnErrorAction.text = getString(error.rActionText)
//        btnErrorAction.setOnClickListener {
//            // model.action(requireActivity() as HostActivity)
//        }
//        error.actionSecondary?.apply {
//            btnErrorActionSecondary.visibility = View.VISIBLE
//            btnErrorActionSecondary.text = error.rActionTextSecondary?.let { getString(it) } ?: ""
//            btnErrorActionSecondary.setOnClickListener {
//                run { error.actionSecondary } ?: return@setOnClickListener
//            }
//        }

//        when (error) {
//            is ViewModelState.Error.Technical ->
//                btnErrorAction.setOnClickListener {
//                    //error.action(requireActivity())
//                }
//            is ViewModelState.Error.Generic -> {
//                btnErrorAction.setOnClickListener {
//                    // error.action(requireActivity() as HostActivity)
//                }
//                btnErrorActionSecondary.setOnClickListener {
//                    //error.secondaryAction(requireActivity() as HostActivity)
//                }
//            }
//        }

    companion object {
        val TAG: String = ErrorFragment::class.simpleName.toString()
        const val KEY_INTENT_ERROR: String = "KEY_INTENT_ERROR"
    }
}
