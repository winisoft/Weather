package stevemerollis.codetrial.weather.paymethods.access

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.paymethods.PayMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PayMethodRepository {

    suspend fun getPreferredMethod(scope: CoroutineScope, token: String): Flow<AsyncResult<PayMethod>>
}