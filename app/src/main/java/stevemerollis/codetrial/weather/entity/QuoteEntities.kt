package stevemerollis.codetrial.weather.request.quote.model

import android.os.Parcelable
import stevemerollis.codetrial.weather.network.converter.EnsuresBoolean
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class QuoteResponse(@Json(name = "quote") val quote: QuoteBase) : Parcelable

@Parcelize
data class QuoteBase (
        val lineItems: LineItems,
        val total: Total,
        val paymentRules: PaymentRules
):Parcelable


@Parcelize
data class LineItems (
        val lineItem: List<LineItem>
): Parcelable

@Parcelize
data class LineItem (
        @EnsuresBoolean
        val isRecurringPayment: Boolean,
        val price: Total,
        val products: Products,

        @Json(name = "offerId")
        val offerID: String,

        val offerSource: String
): Parcelable

@Parcelize
data class Total (
        val amount: String,
        val currencyCode: String
): Parcelable


@Parcelize
data class RetailPrice (
        val amount: String = "",
        val currencyCode: String = ""
): Parcelable

@Parcelize
data class Products (
        val product: List<Product>
): Parcelable

@Parcelize
data class Product (
        val id: String,
        val subscriptionInfo: SubscriptionInfo,
        val retailPrice: RetailPrice? = null,
        val price: List<Total>,
        val taxes: Taxes,
        val prodComponents: ProdComponents
): Parcelable {

    val pricesSum: Double = price
            .filter { it.amount.toDoubleOrNull() != null }
            .map { it.amount.toDouble() }
            .sum()
}

@Parcelize
data class ProdComponents (
        val prodComponent: List<ProdComponent>
): Parcelable {
    val totalDiscounts: Double =
        if (prodComponent.isEmpty() || prodComponent[0].discounts.discount.isEmpty())
            0.0
        else
            prodComponent[0].discounts.sumOfDiscounts
}

@Parcelize
data class ProdComponent (
        val componentID: String,
        val description: String,
        val price: Total,
        val discounts: Discounts
): Parcelable

@Parcelize
data class Discounts (
        val discount: List<Discount>
): Parcelable {

    val sumOfDiscounts: Double get() = discount
        .map { it.value }
        .filter { it.toDoubleOrNull() != null }
        .map { it.toDouble() }
        .sum()
}

@Parcelize
data class Discount (
        val value: String
): Parcelable

@Parcelize
data class SubscriptionInfo (
        val startDateTime: String
): Parcelable

@Parcelize
data class Taxes (
        val taxTotal: Total,
        val tax: List<Tax> = emptyList()
): Parcelable

@Parcelize
data class Tax (
        val name: String,
        val description: String,
        val value: String,
        val currencyCode: String,
        val jurisdictionCodes: JurisdictionCodes,
        val isAccessBased: String
): Parcelable

@Parcelize
data class JurisdictionCodes (
        val code: List<String>
): Parcelable

@Parcelize
data class PaymentRules (
        val pmofRequired: String,
        val currentPMOFValidForOrder: String,
        val validPaymentMethodTypes: ValidPaymentMethodTypes
): Parcelable

@Parcelize
data class ValidPaymentMethodTypes (
        @Json(name = "ValidPaymentMethodTypes")
        val validPaymentMethodTypes: List<String>
): Parcelable