package stevemerollis.codetrial.weather.request.access

import android.content.Intent
import android.content.logText
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.AsyncResult.Error
import stevemerollis.codetrial.weather.async.AsyncResult.Success
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.auth.AuthUtil
import stevemerollis.codetrial.weather.request.OfferModel
import stevemerollis.codetrial.weather.request.quote.*
import stevemerollis.codetrial.weather.request.terms.TermsClient
import stevemerollis.codetrial.weather.fulfillment.Offer
import stevemerollis.codetrial.weather.fulfillment.Offer.Companion.KEY_INTENT_OFFER_ID
import stevemerollis.codetrial.weather.fulfillment.Offer.Companion.KEY_INTENT_OFFER_NAME
import stevemerollis.codetrial.weather.fulfillment.Offer.Companion.KEY_INTENT_OFFER_SOURCE
import stevemerollis.codetrial.weather.fulfillment.Offer.Companion.KEY_INTENT_OFFER_TERMS_TYPE
import stevemerollis.codetrial.weather.fulfillment.model.*
import stevemerollis.codetrial.weather.paymethods.PayMethod
import stevemerollis.codetrial.weather.paymethods.access.PayMethodRepository
import stevemerollis.codetrial.weather.quote.terms.TermsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetOfferUseCaseImpl
@Inject
constructor(
        private val authUtil: AuthUtil,
        private val quoteRepository: QuoteRepository,
        private val payMethodRepository: PayMethodRepository,
        private val termsRepository: TermsRepository
) : GetOfferUseCase {

    override suspend operator fun invoke(
            scope: CoroutineScope, intent: Intent?
    ): Flow<Model<OfferModel>> = flow {

        val (vin: String, offer: Offer) = listOf<String?>(
                intent?.getStringExtra(Offer.KEY_INTENT_VIN),
                intent?.getStringExtra(KEY_INTENT_OFFER_ID),
                intent?.getStringExtra(KEY_INTENT_OFFER_SOURCE),
                intent?.getStringExtra(KEY_INTENT_OFFER_NAME),
        ).run {
            if (any { it.isNullOrEmpty() }) {
                logE { "Invalid initial conditions. \n\t vin: ${this[0]}\t intent: ${intent?.toUri(0)}" }
                emit(Model.Error.InvalidArgs)
                return@flow
            } else {
                logD { "Valid launch arguments\tintent:${intent?.logText}" }
                this
            }
        }.let { it[0]!! to Offer(it[1]!!, it[2]!!, it[3]!!, parseTermsClient(intent)) }


        val token: String? = authUtil.getAuthToken().let { if (it.isSuccess) it.result?.authToken else null }
                .also {
                    if (it == null) { emit(Model.Error.InvalidArgs); return@flow
                    } else logE { "Acquired authToken... $it" }
                }

        quoteRepository.postQuoteRequest(scope, token!!, vin, buildQuoteOrder(offer.id, offer.source))
                .combineTransform(
                        payMethodRepository.getPreferredMethod(scope, token)
                ) {
                    qResult: AsyncResult<QuoteModel>, pResult: AsyncResult<PayMethod> ->

                    if (qResult is Error.Technical || pResult is Error.Technical) {
                        emit(Model.Error.Technical)
                    } else if (qResult !is Success || pResult !is Success) {
                        emit(Model.Error.Generic)
                    } else {
                        val quote: QuoteModel = (qResult as Success<*>).data as QuoteModel
                        val payMethod: PayMethod = (pResult as Success).data
                        val orderSubmission: OrderQuote = buildQuoteOrder(offer.id, offer.source)

                        Model.Success(
                                OfferModel(
                                        name = offer.name,
                                        vin = vin,
                                        payMethod = pResult.data,
                                        quote = qResult.data,
                                        orderSubmission = buildSubmitOrder(offer.id, offer.source, payMethod.id)
                                )
                        ).let {
                            emit(it)
                        }
                    }
                }
    }

    private fun parseTermsClient(intent: Intent?): TermsClient = intent
            ?.getIntExtra(KEY_INTENT_OFFER_TERMS_TYPE, 0)!!.let { TermsClient.from(it) }

    private fun buildQuoteOrder(offerId: String, offerSource: String): OrderQuote =
            OrderQuote(OrderQuoteItems(OrderQuoteItemList(listOf(OrderQuoteItem(offerId, offerSource)))))

}