package stevemerollis.codetrial.weather.fulfillment.access.app

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.fulfillment.model.Order
import stevemerollis.codetrial.weather.fulfillment.access.business.OrderRepository
import stevemerollis.codetrial.weather.fulfillment.model.OrderSubmit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class OrderUseCaseImpl(
    private val orderRepository: OrderRepository
) : OrderUseCase {


    override suspend fun invoke(
            scope: CoroutineScope,
            vin: String,
            accountId: String,
            accessToken: String,
            orderSubmission: OrderSubmit
    ): Flow<AsyncResult<Order>> = flow {

        scope.launch {
            withTimeout(TIMEOUT_DURATION_MS) {

                orderRepository.submitOrder(scope, accessToken, "", vin, orderSubmission).collect {

                }
            }
        }
    }

    companion object {
        const val TIMEOUT_DURATION_MS: Long = 60_000
    }

}