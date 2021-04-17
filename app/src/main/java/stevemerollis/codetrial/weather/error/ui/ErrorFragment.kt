package stevemerollis.codetrial.weather.error.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewBinding
import androidx.lifecycle.LifecycleOwner
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.ViewModelState
import stevemerollis.codetrial.weather.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import stevemerollis.codetrial.weather.util.lo.logI


@AndroidEntryPoint
class ErrorFragment: Fragment(R.layout.fragment_error), LifecycleOwner {

    private val view by viewBinding(FragmentErrorBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        logI { "displaying $TAG" }
        super.onCreateView(inflater, container, savedInstanceState)
        return view.setError(arguments?.getParcelable(KEY_INTENT_ERROR)
            ?: throw IllegalArgumentException("ErrorFragment launched without required Model.Error arg")
        ).root
    }

    private fun FragmentErrorBinding.setError(error: ViewModelState.Error) = view.apply {
        tvErrorTitle.text = getString(error.rTitle)
        tvErrorMessage.text = getString(error.rMessage)
        btnErrorAction.text = getString(error.rActionText)
        btnErrorAction.setOnClickListener {
            // model.action(requireActivity() as HostActivity)
        }
        error.actionSecondary?.apply {
            btnErrorActionSecondary.visibility = View.VISIBLE
            btnErrorActionSecondary.text = error.rActionTextSecondary?.let { getString(it) } ?: ""
            btnErrorActionSecondary.setOnClickListener {
                run { error.actionSecondary } ?: return@setOnClickListener
            }
        }

        when (error) {
            is ViewModelState.Error.Technical ->
                btnErrorAction.setOnClickListener {
                    //error.action(requireActivity())
                }
            is ViewModelState.Error.Generic -> {
                btnErrorAction.setOnClickListener {
                    // error.action(requireActivity() as HostActivity)
                }
                btnErrorActionSecondary.setOnClickListener {
                    //error.secondaryAction(requireActivity() as HostActivity)
                }
            }
        }
    }

    companion object {
        val TAG: String = ErrorFragment::class.simpleName.toString()
        const val KEY_INTENT_ERROR: String = "KEY_INTENT_ERROR"
    }
}
