package stevemerollis.codetrial.weather.validate.rules.business

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.entity.CodeRequestResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


interface OtpRepository {

    suspend fun confirmPasscode(
            scope: CoroutineScope,
            jwt: String
    ): Flow<AsyncResult<ConfirmCodeResponse>>

    suspend fun requestPasscode(
            scope: CoroutineScope,
            authToken: String,
            contactMethod: CodeRequestBody
    ): Flow<AsyncResult<CodeRequestResponse>>
}
