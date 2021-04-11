package stevemerollis.codetrial.weather.request.quote

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
data class QuoteModel(
        val subtotal: String,
        val taxesAndFees: String,
        val credit: String?,
        val total: String,
        val isRecurring: Boolean
): Parcelable

data class QuoteRequest(val authToken: String, val vin: String, val body: OrderQuote)

@JsonClass(generateAdapter = true)
data class OrderQuote(
    val order: OrderQuoteItems
)

data class OrderQuoteItems(
    val lineItems: OrderQuoteItemList
)

data class OrderQuoteItemList(
    val lineItem: List<OrderQuoteItem>
)

data class OrderQuoteItem(
    val offerId: String,
    val offerSource: String
)
