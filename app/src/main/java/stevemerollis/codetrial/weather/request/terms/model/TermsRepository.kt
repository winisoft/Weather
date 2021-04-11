package stevemerollis.codetrial.weather.request.terms.model

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.request.terms.Terms
import stevemerollis.codetrial.weather.request.terms.TermsClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred


interface TermsRepository {

    suspend fun termsAsync(scope: CoroutineScope, termsClient: TermsClient): Deferred<AsyncResult<Terms>>
}