package stevemerollis.codetrial.weather.contact.data.access

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.AsyncResult.Success
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.contact.data.AccountOwner
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException


class ContactInfoRepositoryImpl
constructor(
        private val networkHelper: NetworkHelper
): ContactInfoRepository {

    /**
     * Continues the flow QuoteRequestState flow by getting the user's registered contact methods
     * and processing the resulting data, to append relevant data to the quote request's state.
     */
    override suspend fun getAccountOwner(
        scope: CoroutineScope,
        authToken: String
    ): Flow<AsyncResult<AccountOwner>> = flow {
        try {
            val result = networkHelper.userInfo(scope, authToken)
            emit(Success(result.userInfo.accountOwner))
        } catch(e: Exception) {
            logE(e) { e.message ?: "" }
            if (e is IOException) {
                emit(AsyncResult.Error.Technical)
            } else {
                emit(AsyncResult.Error.Generic)
            }
        }
    }.flowOn(CoroutineDsl.io)
}