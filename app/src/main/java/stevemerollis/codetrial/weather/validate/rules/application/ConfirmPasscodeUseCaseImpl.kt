package stevemerollis.codetrial.weather.validate.rules.application

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.auth.AuthUtil
import stevemerollis.codetrial.weather.validate.rules.business.ConfirmCodeResponse
import stevemerollis.codetrial.weather.validate.rules.business.OtpRepository
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject


class ConfirmPasscodeUseCaseImpl
@Inject
constructor(
        private val authUtil: AuthUtil,
        private val otpRepository: OtpRepository
) : ConfirmPasscodeUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override suspend operator fun invoke(scope: CoroutineScope, vin: String, otp: String)
            : Flow<Model<ConfirmCodeResponse>> = flow<Model<ConfirmCodeResponse>> {

        logD { "Getting id token... " }

        val idToken: String = authUtil.getIdToken().result?.authToken ?: ""
        if (idToken.isEmpty()) {
            logD { "Failed to pull id token with AuthUtil in " }
            emit(Model.Error.Generic)
            return@flow
        }

        val decodedJWT: JWT = JWT.getDecoder().decode(idToken)

        if (idToken.isNotEmpty()) {
            logD { "building RequestBody..." }

            val encodedJWT: JWT = JWT().apply {
                setSubject(decodedJWT.subject)
                        .addClaim("assertion", "")
                        .addClaim("device_id", vin)
                        .addClaim("client_id", "AUTHENTICATOR_V2")
                        .addClaim("grant_type", "urn:onstar:params:oauth:grant-type:jwt-bearer-otp")
                        .addClaim("scope", "priv onstar")
                        .addClaim("otp", otp)
                        .addClaim("subject", "444eac13-03a2-4549-9134-e53ab9f56472")
                        .addClaim("nonce", UUID.randomUUID().toString())
                        .addClaim("timestamp", ZonedDateTime.now(ZoneOffset.UTC))
                        .run {
                            JWT.getEncoder().encode(this, HMACSigner.newSHA256Signer(CLIENT_SECRET))
                        }
            }

            logD { "Formed our jwt.. ${encodedJWT.toString()} " }

            otpRepository.confirmPasscode(scope, encodedJWT.toString())
                    .transform { callResult: AsyncResult<ConfirmCodeResponse> ->
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
    }


    companion object {
        val TAG: String = ConfirmPasscodeUseCase::class.simpleName.toString()
        const val CLIENT_SECRET: String = "9gg42164-19ce-42zz-b3me-2qq39ez43ab2"
    }
}
