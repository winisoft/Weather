package stevemerollis.codetrial.weather.methods.access

import stevemerollis.codetrial.weather.network.converter.EnsuresBoolean
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PayMethodResponse(
    val paymentMethods: PaymentMethods
)

@Json(name = "paymentMethods")
class PaymentMethods(
    val paymentMethod: List<PaymentMethod>
)

@Json(name = "paymentMethod")
class PaymentMethod(
    val paymentMethodId: String,
    val creditCard: Card
)

class Card(
    val type: String,
    val number: String,
    @EnsuresBoolean @Json(name = "preferredMethod") var isPreferred: Boolean
)