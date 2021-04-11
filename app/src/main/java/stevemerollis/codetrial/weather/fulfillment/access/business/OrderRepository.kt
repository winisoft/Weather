package stevemerollis.codetrial.weather.fulfillment.access.business

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.fulfillment.model.Order
import stevemerollis.codetrial.weather.fulfillment.model.OrderResponse
import stevemerollis.codetrial.weather.fulfillment.model.OrderSubmit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OrderRepository {

    suspend fun submitOrder(
            scope: CoroutineScope,
            accessToken: String,
            accountId: String,
            vin: String,
            orderSubmission: OrderSubmit
    ): Flow<AsyncResult<Order.OrderStatus>>

    suspend fun requestOrderUpdate(
            scope: CoroutineScope,
            accessToken: String,
            url: String
    ): Flow<Response<OrderResponse>>
}