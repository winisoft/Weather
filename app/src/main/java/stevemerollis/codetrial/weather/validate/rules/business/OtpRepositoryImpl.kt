package stevemerollis.codetrial.weather.validate.rules.business

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.entity.CodeRequestResponse
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.util.lo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class OtpRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper
) : OtpRepository {

    override suspend fun confirmPasscode(scope: CoroutineScope, jwt: String)
            : Flow<AsyncResult<ConfirmCodeResponse>> = flow<AsyncResult<ConfirmCodeResponse>> {
        try {
            emit(AsyncResult.Success<ConfirmCodeResponse>(networkHelper.confirmPasscode(scope, jwt)))
        } catch (ioe: IOException) {
            lo.logE(ioe) { "" }
            emit(AsyncResult.Error.Technical)
            return@flow
        } catch(e: Exception) {
            lo.logE(e) { e.message ?: "" }
            emit(AsyncResult.Error.Generic)
            return@flow
        }
    }

    override suspend fun requestPasscode(
            scope: CoroutineScope,
            authToken: String,
            contactMethod: CodeRequestBody
    ): Flow<AsyncResult<CodeRequestResponse>> = flow {
        try {
            emit(AsyncResult.Success(networkHelper.requestPasscode(authToken, contactMethod)))
        } catch (ioe: IOException) {
            lo.logE(ioe) { "" }
            emit(AsyncResult.Error.Technical)
            return@flow
        } catch(e: Exception) {
            lo.logE(e) { e.message ?: "" }
            emit(AsyncResult.Error.Generic)
            return@flow
        }
    }
}
