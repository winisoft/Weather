package stevemerollis.codetrial.weather.fulfillment.model

import stevemerollis.codetrial.weather.network.converter.InformsOrderStatus
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Order(
        val requestTime: String,
        val url: String,
        @InformsOrderStatus
        val status: OrderStatus,
        val type: String
) {

    enum class OrderStatus {
        SUCCESS,
        IN_PROGRESS,
        FAILURE,
        PENDING,
        CANCELLED,
        PARTIAL_SUCCESS,
        UNKNOWN; // steve's addition

        companion object {
            fun from(nextString: String?): OrderStatus = when (nextString ?: OrderStatus.UNKNOWN) {
                "SUCCESS" -> SUCCESS
                "IN_PROGRESS" -> IN_PROGRESS
                "FAILURE" -> FAILURE
                "PENDING" -> PENDING
                "CANCELLED" -> CANCELLED
                "PARTIAL_SUCCESS" -> PARTIAL_SUCCESS
                else -> UNKNOWN
            }

            /**
             * The set of [OrderStatus] for which we should disregard and continue awaiting the final result
             */
            val unfulfilledStatuses: Set<OrderStatus> = setOf(IN_PROGRESS, PENDING)

            /**
             * The set of [OrderStatus] for which we should discontinue waiting and inform the user of our result
             */
            val failedStatuses: Set<OrderStatus> = setOf(FAILURE, CANCELLED)

            val successStatuses: Set<OrderStatus> = setOf(SUCCESS, PARTIAL_SUCCESS)
        }
    }
}