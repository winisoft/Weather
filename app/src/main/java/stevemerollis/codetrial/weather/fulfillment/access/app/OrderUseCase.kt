package stevemerollis.codetrial.weather.fulfillment.access.app

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.fulfillment.model.Order
import stevemerollis.codetrial.weather.fulfillment.model.OrderSubmit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface OrderUseCase {

    suspend fun invoke(
            scope: CoroutineScope,
            vin: String,
            accountId: String,
            accessToken: String,
            orderSubmission: OrderSubmit
    ): Flow<AsyncResult<Order>>
}