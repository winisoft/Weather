package stevemerollis.codetrial.weather.fulfillment.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

fun buildSubmitOrder(
        offerId: String,
        offerSource: String,
        paymentMethodId: String
): OrderSubmit = OrderSubmit(
       OrderSubmitItems(
           OrderSubmitItemList(listOf<OrderSubmitItem>(
                OrderSubmitItem(offerId, offerSource)
            ))
       ),
       paymentMethod = SimplePayment(paymentMethodId)
   )

@Parcelize
@JsonClass(generateAdapter = true)
data class OrderSubmit(
    val order: OrderSubmitItems,
    val paymentMethod: SimplePayment
): Parcelable

@Parcelize
data class OrderSubmitItems(
        val lineItems: OrderSubmitItemList
): Parcelable

@Parcelize
data class OrderSubmitItemList(
        @Json(name = "lineItem") val lineItemList: List<OrderSubmitItem>
): Parcelable

@Parcelize
data class OrderSubmitItem(
        @Json(name = "offerId") val offerId: String,
        @Json(name = "offerSource") val offerSource: String
): Parcelable

@Parcelize
data class SimplePayment(
    val paymentMethodId: String
): Parcelable