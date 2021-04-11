package stevemerollis.codetrial.weather.validate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.*
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.databinding.FragmentValidateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ValidateFragment: Fragment(), LifecycleOwner {

    private val viewModel: ValidateViewModel by viewModels()
    private var _binding: FragmentValidateBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View = FragmentValidateBinding.inflate(inflater, container, false).apply {
        logD { "onCreateView: $TAG" }
        viewModel.viewState.observe(viewLifecycleOwner, observer)
        _binding!!.initView()
    }.root

    private fun FragmentValidateBinding.initView() {
        listOf(btnNumpad0, btnNumpad1, btnNumpad2, btnNumpad3, btnNumpad4, btnNumpad5, btnNumpad6,
                btnNumpad7, btnNumpad8, btnNumpad9, btnNumpad0, btnNumpadBack, btnValidateResend
        ).forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.handleButtonPress(UserInputEvent.getFromOrdinal(index))
            }
        }
        btnValidateBack.setOnClickListener {
            navController.navigate(R.id.action_validateFragment_to_permissionFragment)
        }
        viewModel.viewState.observe(viewLifecycleOwner, observer)
    }

    private val observer: Observer<ValidateViewState> = Observer {
        when(it) {
            is ValidateViewState.Error -> showError(it.model)
            is ValidateViewState.AcceptingInput -> acceptingInput(it.currentInput)
            is ValidateViewState.CodeSubmitted -> codeSubmitted()
            is ValidateViewState.PurchaseConfirmed -> purchaseConfirmed()
            is ValidateViewState.Loading -> showLoading()
        }
    }

    private fun showError(error: Model.Error) {

    }

    private fun showLoading() {

    }

    private fun acceptingInput(currentInput: String) = binding?.apply {
        progressViewValidate.visibility = View.GONE
        validateInputText.text = currentInput
    }

    private fun codeSubmitted() {

    }

    private fun purchaseConfirmed() {
        navController.navigate(R.id.action_validateFragment_to_confirmationFragment)
    }

    companion object {
        val TAG: String = ValidateFragment::class.simpleName.toString()
    }
}

