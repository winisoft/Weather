package stevemerollis.codetrial.weather.request.quote

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.request.quote.model.LineItem
import stevemerollis.codetrial.weather.request.quote.model.Product
import stevemerollis.codetrial.weather.request.quote.model.QuoteResponse
import dagger.hilt.android.scopes.ActivityScoped
import gmkotlin.asPrice
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton



class QuoteRepositoryImpl
@Inject
constructor(
        val networkHelper: NetworkHelper
): QuoteRepository {

    val QuoteResponse.invalidReason: String?
        get() = when {
            quote.lineItems.lineItem.isEmpty() -> "has no line items"
            quote.lineItems.lineItem[0].products.product.isEmpty() -> "has no products"
            quote.lineItems.lineItem[0].products.product[0].price.isEmpty() -> "has no price associated"
            else -> "has no line item matching requested"
        }

    private fun QuoteResponse.isDataValid(orderQuote: OrderQuote): Boolean =
            quote.lineItems.lineItem.run {
                //ensure a lineItem contains the requested order
                isNotEmpty() && any { foundItem: LineItem ->
                    orderQuote.order.lineItems.lineItem[0].run {
                        offerId == foundItem.offerID && offerSource == foundItem.offerSource
                    }
                } && get(0).products.product.let { it.isNotEmpty() && it[0].price.isNotEmpty() }
            }

    override suspend fun postQuoteRequest(
            scope: CoroutineScope,
            authToken: String,
            vin: String,
            orderQuote: OrderQuote
    ): Flow<AsyncResult<QuoteModel>> = flow {

        scope.launch {

            val response: QuoteResponse = networkHelper.quote(scope, authToken, vin, orderQuote)

            val firstLineItem: LineItem = response.quote.lineItems.lineItem[0]
            val firstProduct: Product = firstLineItem.products.product[0]
            val currencySymbol: String = Currency.getInstance(response.quote.total.currencyCode)
                    .run { Locale.getDefault(Locale.Category.DISPLAY).toString() }

            QuoteModel(
                    isRecurring = firstLineItem.isRecurringPayment,
                    subtotal = "$currencySymbol${firstProduct.pricesSum.asPrice()}",
                    taxesAndFees = "$currencySymbol${firstProduct.taxes.taxTotal.amount.asPrice()}",
                    credit = "$currencySymbol${firstProduct.prodComponents.totalDiscounts.asPrice()}",
                    total = "$currencySymbol${response.quote.total.amount.asPrice()}"
            ).also {
                emit(AsyncResult.Success(it))
            }
        }
    }
}
