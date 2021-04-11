package stevemerollis.codetrial.weather.validate.rules.application

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.entity.CodeRequestResponse
import stevemerollis.codetrial.weather.auth.AuthUtil
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.validate.rules.business.OtpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject



class RequestPasscodeUseCaseImpl
@Inject
constructor(
    private val authUtil: AuthUtil,
    private val otpRepository: OtpRepository
    ) : RequestPasscodeUseCase {

    private lateinit var vinNum: String
    private lateinit var assertionString: String

    override suspend operator fun invoke(
        scope: CoroutineScope,
        vin: String,
        contactMethod: CodeRequestBody
    ): Flow<Model<CodeRequestResponse>> = flow<Model<CodeRequestResponse>> {

        logD { "Requesting passcode.." }

        val authToken: String? = authUtil.getAuthToken().result?.authToken ?: ""
        if (authToken.isNullOrEmpty()) {
            logD { "Failed to pull id token with AuthUtil at " }
            emit(Model.Error.Generic)
            return@flow
        }

        otpRepository.requestPasscode(scope, authToken, contactMethod)
                .transform { callResult: AsyncResult<CodeRequestResponse> ->
                    when (callResult) {
                        is AsyncResult.Error.Generic, AsyncResult.Error.Technical ->
                            emit(Model.from(callResult as AsyncResult.Error))
                        !is AsyncResult.Success ->
                            throw IllegalArgumentException("Not a valid return type: ${callResult.javaClass}")
                        else ->
                            emit(Model.Success(callResult))
                    }
                }
        }

    companion object {
        val TAG: String = RequestPasscodeUseCaseImpl::class.simpleName.toString()
        const val PASS_GRANT_SCOPE: String = "priv onstar"
        const val CLIENT_ID: String = "AUTHENTICATOR_V2"
        const val CLIENT_SECRET: String = "9gg42164-19ce-42zz-b3me-2qq39ez43ab2"
        const val GRANT_TYPE: String = "urn:onstar:params:oauth:grant-type:jwt-bearer-otp"
        const val SCOPE_REQUESTED: String = "priv onstar"
    }
}