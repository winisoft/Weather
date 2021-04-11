package stevemerollis.codetrial.weather.request.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.databinding.FragmentOfferBinding
import stevemerollis.codetrial.weather.request.OfferModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OfferFragment: Fragment(), LifecycleOwner {

    private val viewModel: OfferViewModel by activityViewModels()
    private var _binding: FragmentOfferBinding? = null
    private val binding: FragmentOfferBinding? get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentOfferBinding.inflate(inflater, container, false).run {
            _binding = this
            viewModel.viewState.observe(viewLifecycleOwner, observer)
            viewModel.handleIntent(activity?.intent)
            root
        }

    private val observer: Observer<OfferViewState> = Observer {
        logD { "$TAG observes state changed to: $it" }
        when(it) {
            is OfferViewState.FinishApp -> activity?.finish()
            is OfferViewState.Loading -> showSpinner()
            is OfferViewState.Error -> showError(it.value)
            is OfferViewState.RenderUI -> setData(it.offerModel)
        }
    }

    private fun showSpinner() { binding?.progressIndicator?.visibility = View.VISIBLE }

    private fun hideSpinner() { binding?.progressIndicator?.visibility = View.GONE }

    private fun setData(model: OfferModel): OfferModel = model.apply {
        hideSpinner()
        binding?.apply {
            //tvT .text = model.name
            //back .setOnClickListener { activity?.finish() }
        }
    }

    private fun showError(error: Model.Error) {
        hideSpinner()
        //val action = OfferFragmentDirections
        //navController.navigate(OfferFragmentDirections) actionShowError(error))
    }

    companion object {
        val TAG = OfferFragment::class.simpleName!!
        const val KEY_INTENT: String = "KEY_INTENT"
        const val KEY_QUOTE_MODEL: String = "KEY_QUOTE_MODEL"
    }
}