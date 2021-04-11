package stevemerollis.codetrial.weather.fulfillment.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.whenResumed
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.fulfillment.model.FinishedIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_confirmation.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SuccessFragment: Fragment(R.layout.layout_confirmation), Observer<Unit?> {

    private val viewModel: SuccessViewModel by viewModels()

    private val alertClientCompleted: FinishedIntent? = arguments?.get(FinishedIntent.TAG) as FinishedIntent?

    init {
        viewModel.apply {
            viewState.observe(viewLifecycleOwner, this@SuccessFragment)
            viewModelScope.launch {
                viewLifecycleOwner.whenResumed {
                    viewModel.onSuccessFragmentVisible()
                }
            }
        }
    }

    override fun onChanged(visibilityTimerExpired: Unit?) {
        activity?.apply {
            GmToast.makeText(this, getString(R.string.success_toast_message)).show()
            sendBroadcast(alertClientCompleted ?: FinishedIntent(FinishedIntent.Reason.Unknown))
            finish()
        }
    }
}