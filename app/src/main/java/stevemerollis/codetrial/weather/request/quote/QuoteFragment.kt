package stevemerollis.codetrial.weather.request.quote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.databinding.FragmentLineItemsBinding
import stevemerollis.codetrial.weather.request.OfferModel
import stevemerollis.codetrial.weather.request.ui.OfferViewModel
import stevemerollis.codetrial.weather.request.ui.OfferViewState
import stevemerollis.codetrial.weather.paymethods.PayMethod
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuoteFragment: Fragment(), LifecycleOwner {

    private val viewModel: OfferViewModel by activityViewModels()
    private var _binding: FragmentLineItemsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        logI { "onCreateView: $TAG" }
        _binding = FragmentLineItemsBinding.inflate(inflater, container, false)
        viewModel.handleIntent(activity?.intent)
        viewModel.viewState.observe(viewLifecycleOwner, observer)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val observer: Observer<OfferViewState> = Observer {
        logD { "$TAG observes state changed to: $it" }
        if (it is OfferViewState.RenderUI)
            setData(requireContext(), it.offerModel)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(context: Context, model: OfferModel): OfferModel = model.apply {
        binding?.apply {
            tvCreditCard.text = "${context.getString(R.string.mybrand_10_2_credit_csm)} ${model.payMethod.lastFour}"
            val renewing: String = if (model.quote.isRecurring) getString(R.string.monthly) else ""
            tvDiscount.text = "${model.quote.credit}$renewing"
            tvSubtotal.text = "${model.quote.subtotal}$renewing"
            tvTaxes.text = "${model.quote.taxesAndFees}$renewing"
            tvTotal.text = "${model.quote.total}$renewing"
            when (model.payMethod.type) {
                PayMethod.CardType.Visa -> R.drawable.ic_icn_discover
                PayMethod.CardType.MasterCard -> R.drawable.ic_icn_mastercard
                PayMethod.CardType.AmericanExpress -> R.drawable.ic_icn_amex
                PayMethod.CardType.Discover -> R.drawable.ic_icn_discover
                else -> R.drawable.card_list_item_back
            }.apply {
                imgCreditCard.background = ContextCompat.getDrawable(requireContext(), this)
            }
            if (model.quote.isRecurring) {
                tvPromotionLabel.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        val TAG = QuoteFragment::class.simpleName!!
        const val KEY_INTENT: String = "KEY_INTENT"
        const val KEY_OFFER_MODEL: String = "KEY_OFFER_MODEL"
    }
}