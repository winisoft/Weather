package stevemerollis.codetrial.weather.request.terms.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import stevemerollis.codetrial.weather.databinding.FragmentTermsSummaryBinding
import stevemerollis.codetrial.weather.request.OfferModel
import stevemerollis.codetrial.weather.request.ui.OfferViewModel
import stevemerollis.codetrial.weather.request.ui.OfferViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@AndroidEntryPoint
class TermsSummaryFragment: Fragment(), LifecycleOwner {

    private val viewModel: OfferViewModel by viewModels()
    private lateinit var _binding: FragmentTermsSummaryBinding
    private val binding: FragmentTermsSummaryBinding get() = _binding

    private val _approvedState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val approvedState: StateFlow<Boolean> get() = _approvedState

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        logD { "displaying $TAG" }

        return FragmentTermsSummaryBinding.inflate(inflater, container, false).run {
            _binding = this
            viewModel.viewState.observe(viewLifecycleOwner, observer)
            _binding.root
        }
    }

    private val observer: Observer<OfferViewState> = Observer { state ->
        logD { "$TAG observes state changed to: $state" }

        if (state is OfferViewState.RenderUI) {
            val offer: OfferModel = state.offerModel
            binding.checkboxTermsApproved.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
                _approvedState.value = _approvedState.value.not()
            }
            binding.btnPurchase.setOnClickListener { purchase ->
                //OfferFragment (vin, payMethod.id)
            }
//                btnViewTerms.setOnClickListener {
//                    OfferFragmentDirections.actionOfferViewTerms()
//                }
            }
    }

    companion object {
        val TAG: String = TermsSummaryFragment::class.simpleName.toString()
    }
}