package stevemerollis.codetrial.weather.validate.rules.application

import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.entity.CodeRequestResponse
import stevemerollis.codetrial.weather.validate.rules.business.ConfirmCodeResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ConfirmPasscodeUseCase {

    suspend operator fun invoke(scope: CoroutineScope, vin: String, otp: String):  Flow<Model<ConfirmCodeResponse>>

}
