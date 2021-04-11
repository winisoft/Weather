package stevemerollis.codetrial.weather.error.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewBinding
import androidx.lifecycle.LifecycleOwner
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_error.*


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

    private fun FragmentErrorBinding.setError(error: Model.Error) = view.apply {
        tvErrorTitle.text = getString(error.titleRes)
        tvErrorMessage.text = getString(error.contentRes)
        btnErrorAction.text = getString(error.actionTextRes)
        btnErrorAction.setOnClickListener {
            // model.action(requireActivity() as HostActivity)
        }
        error.secondaryAction?.apply {
            btnErrorActionSecondary.visibility = View.VISIBLE
            btnErrorActionSecondary.text = error.secondaryTextRes?.let { getString(it) } ?: ""
            btnErrorActionSecondary.setOnClickListener {
                run { error.secondaryAction } ?: return@setOnClickListener
            }
        }

        when (error) {
            is Model.Error.InvalidArgs ->
                btnErrorAction.setOnClickListener {
                    //error.action(requireActivity())
                }
            is Model.Error.Generic -> {
                btnErrorAction.setOnClickListener {
                    // error.action(requireActivity() as HostActivity)
                }
                btnErrorActionSecondary.setOnClickListener {
                    //error.secondaryAction(requireActivity() as HostActivity)
                }
            }
            is Model.Error.Buy009 -> { }
            is Model.Error.Technical -> { }
            is Model.Error.Ons551 -> { }
        }
    }

    companion object {
        val TAG: String = ErrorFragment::class.simpleName.toString()
        const val KEY_INTENT_ERROR: String = "KEY_INTENT_ERROR"
    }
}