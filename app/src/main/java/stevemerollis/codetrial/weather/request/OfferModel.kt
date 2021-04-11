package stevemerollis.codetrial.weather.request

import android.os.Parcelable
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.request.quote.QuoteModel
import stevemerollis.codetrial.weather.fulfillment.model.OrderSubmit
import stevemerollis.codetrial.weather.paymethods.PayMethod

import kotlinx.android.parcel.Parcelize


@Parcelize
class OfferModel(
        val name: String,
        val vin: String,
        val payMethod: PayMethod,
        val quote: QuoteModel,
        val orderSubmission: OrderSubmit
): Parcelable {

    enum class CardType(val value: Int) {

        Visa(R.drawable.ic_icn_visa),
        Mastercard(R.drawable.ic_icn_mastercard),
        AmericanExpress(R.drawable.ic_icn_amex),
        Discover(R.drawable.ic_icn_discover);
    }

    companion object {
        val TAG: String = OfferModel::class.simpleName!!
    }
}