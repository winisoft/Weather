package stevemerollis.codetrial.weather.contact.data.access

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.contact.data.AccountOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


interface ContactInfoRepository {

    suspend fun getAccountOwner(
            scope: CoroutineScope,
            authToken: String
    ): Flow<AsyncResult<AccountOwner>>

}