package stevemerollis.codetrial.weather.contact.domain

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.contact.data.access.ContactInfoRepository
import stevemerollis.codetrial.weather.contact.data.AccountOwner
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.auth.AuthUtil
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton


@ExperimentalCoroutinesApi
class GetContactInfoUseCaseImpl
constructor(
        private val authUtil: AuthUtil,
        private val contactInfoRepository: ContactInfoRepository
): GetContactInfoUseCase {


    override suspend operator fun invoke(scope: CoroutineScope): Flow<Model<List<ContactMethod>>> {

        val token: String? = authUtil.getAuthToken().result?.authToken
        if (token == null)
            return flowOf(Model.Error.Generic)
        else
            return contactInfoRepository.getAccountOwner(scope, token).transform { accOwnerResult ->

                if (accOwnerResult !is AsyncResult.Success<*>)
                    emit(Model.from(accOwnerResult as AsyncResult.Error))
                else
                    (accOwnerResult.data as AccountOwner).run {
                        listOf(ContactMethod.Email(email, threeAlphaBeforeDomain(email)))
                            .plus(
                                listOf(phone, mobilePhone, workPhone).filter { number ->
                                    !number.isNullOrEmpty()
                                }.map { number ->
                                    ContactMethod.Phone(number!!, lastFourDigits(number))
                                }
                            )
                    }.let { methods -> emit(Model.Success(methods)) }
            }
        }

    private fun lastFourDigits(text: String): String =
            if (text.isEmpty()) "" else text.substring(text.length - 5, text.length - 1)

    private fun threeAlphaBeforeDomain(text: String): String = if (text.isEmpty()) "" else {
        val fourBackTopsPos: Int = (text.indexOfLast { ch -> ch == '@' } - 3).coerceAtLeast(0)
        text.substring(fourBackTopsPos, text.length)
    }
}

