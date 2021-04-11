package stevemerollis.codetrial.weather.fulfillment.access.business

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.fulfillment.model.Order
import stevemerollis.codetrial.weather.fulfillment.model.OrderResponse
import stevemerollis.codetrial.weather.fulfillment.model.OrderSubmit
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject


class OrderRepositoryImpl
@Inject
constructor(
   val networkHelper: NetworkHelper
): OrderRepository {

    override fun submitOrder(
            scope: CoroutineScope,
            accessToken: String,
            accountId: String,
            vin: String,
            orderSubmission: OrderSubmit
    ): Flow<AsyncResult<Order.OrderStatus>> = flow<AsyncResult<Order.OrderStatus>> {

        scope.launch {

            networkHelper.orderPlacement(scope, accessToken, accountId, vin, orderSubmission).let { response: Response<OrderResponse> ->

                if (response.isSuccessful && response.code() == 202 && response.body()?.order?.status != null){

                    var url: String = response.body()!!.order.url

                    requestOrderUpdate(this, accessToken, url)
                        .flowOn(scope.coroutineContext + CoroutineDsl.io)
                        .retryWhen { cause: Throwable?, _: Long ->
                            when (cause) {
                                is IOException -> {
                                    logE(cause) { cause.message ?: "acceptable failure..." }
                                    emit(AsyncResult.Error.Technical)
                                    false
                                }
                                is CancellationException, is TimeoutException -> {
                                    logE(cause) { cause.message ?: "acceptable failure..." }
                                    emit(AsyncResult.Error.Generic)
                                    false
                                }
                                else -> true
                            }
                        }.transform {

                            when {
                                (response.isSuccessful && response
                                        .body()?.order?.status!! in Order.OrderStatus.successStatuses) -> {
                                    logD { "Successful purchase." }
                                    emit(AsyncResult.Success(Unit))
                                }
                                (response.isSuccessful && response
                                        .body()?.order?.status!! in Order.OrderStatus.failedStatuses) -> {
                                    logE { "Failed : ${response.body()?.order!!.status}" }
                                    emit(AsyncResult.Error.Generic)
                                }
                                (response.isSuccessful) -> {
                                    scope.coroutineContext.run {
                                        when {
                                            this.toString().contains("ONS-551") -> {
                                                logD { "ONS-551 error" }
                                                emit(AsyncResult.Error.Ons551)
                                            }
                                            this.toString().contains("BUY-009") -> {
                                                logD { "BUY-009" }
                                                emit(AsyncResult.Error.Buy009)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    override suspend fun requestOrderUpdate(scope: CoroutineScope, accessToken: String, url: String): Flow<Response<OrderResponse>> =
        flowOf(networkHelper.orderStatus(scope, accessToken, url))

    fun Flow<Response<OrderResponse>>.emitSuccess(): Flow<*>
    = transform {
        response: Response<OrderResponse> ->

            if (response.isSuccessful
            && response.body()?.order?.status.run {
                equals(Order.OrderStatus.SUCCESS) || equals(Order.OrderStatus.PARTIAL_SUCCESS)
            }){
                emit(AsyncResult.Success(Unit))
            } else {
                emit(response)
            }
    }
}