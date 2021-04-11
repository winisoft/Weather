package stevemerollis.codetrial.weather.fulfillment.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponse(
    val order: Order
)