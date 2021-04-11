package stevemerollis.codetrial.weather.validate.rules.application

import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.contact.domain.ContactMethod
import stevemerollis.codetrial.weather.entity.CodeRequestResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


interface RequestPasscodeUseCase {

    suspend operator fun invoke(
        scope: CoroutineScope,
        vin: String,
        contactMethod: CodeRequestBody
    ): Flow<Model<CodeRequestResponse>>
}
