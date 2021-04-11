package stevemerollis.codetrial.weather.paymethods.access

import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.methods.access.PayMethodResponse
import stevemerollis.codetrial.weather.network.state.NetStateUtil
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.paymethods.*
import stevemerollis.codetrial.weather.paymethods.PayMethod.CardType.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject


class PayMethodRepositoryImpl
@Inject
constructor(
        val networkHelper: NetworkHelper,
        val netStateUtil: NetStateUtil
) : PayMethodRepository {

    override suspend fun getPreferredMethod(
        scope: CoroutineScope,
        token: String
    ): Flow<AsyncResult<PayMethod>> = flow {

        val result = networkHelper.payMethods(scope, token)

        if (result.isDataValid)
            emit(AsyncResult.Error.Generic).also { _ ->
                logE { "Invalid PayMethodsResponse received from API. Cause: ${result.dataInvalidReason}" }
            }
        else
            result.paymentMethods.paymentMethod.first { it.creditCard.isPreferred }.run {
                logD {
                    "got preferred payment method. " +
                            "id: $paymentMethodId type: ${creditCard.type} number: ${creditCard.number}"
                }
                AsyncResult.Success(
                    PayMethod(
                        id = paymentMethodId,
                        lastFour = creditCard.number,
                        type = when (creditCard.type.toLowerCase(Locale.ROOT)) {
                            Visa.value -> Visa
                            MasterCard.value -> MasterCard
                            AmericanExpress.value -> AmericanExpress
                            Discover.value -> Discover
                            else -> throw IllegalArgumentException("Unknown credit card type")
                        }
                    )
                ).let {
                    emit(it)
                }
            }
        }

    private val PayMethodResponse.isDataValid: Boolean
        get() = paymentMethods.paymentMethod.run {
            any {
                it.creditCard.isPreferred
                && it.creditCard.number.isNotEmpty()
                && it.creditCard.type.isEmpty()
            } && single {
                it.creditCard.isPreferred
            }.let {
                PayMethod.CardType.getById(it.creditCard.type.toString()) != null
            }
        }

    private val PayMethodResponse.dataInvalidReason: String?
        get() = paymentMethods.paymentMethod.find {
            it.creditCard.isPreferred && it.creditCard.number.isNotEmpty() && it.creditCard.type.isNullOrEmpty()
        }?.let {
            PayMethod.CardType.getById(it.creditCard.type.toString())?.let { null } ?: "Unknown credit card type"
        } ?: "Missing required field"

    companion object {
        val TAG: String = PayMethodRepositoryImpl::class.simpleName!!
    }
}
