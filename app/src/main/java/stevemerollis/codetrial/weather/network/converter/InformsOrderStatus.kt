package stevemerollis.codetrial.weather.network.converter

import stevemerollis.codetrial.weather.fulfillment.model.Order
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson


@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InformsOrderStatus

class OrderStatusAdapter {

    @FromJson
    @InformsOrderStatus
    fun fromJson(jsonReader: JsonReader): Order.OrderStatus {

        return when (jsonReader.peek()) {
            JsonReader.Token.STRING -> Order.OrderStatus.from(jsonReader.nextString())
            else -> Order.OrderStatus.UNKNOWN
        }
    }

    @ToJson
    fun toJson(@InformsOrderStatus value: Order.OrderStatus): String = value.name

}