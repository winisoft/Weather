package stevemerollis.codetrial.weather.request.quote

import stevemerollis.codetrial.weather.async.AsyncResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun postQuoteRequest(
            scope: CoroutineScope,
            authToken: String,
            vin: String,
            orderQuote: OrderQuote
    ): Flow<AsyncResult<QuoteModel>>
}