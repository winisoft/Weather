package stevemerollis.codetrial.weather.fulfillment

import stevemerollis.codetrial.weather.request.terms.TermsClient


data class Offer(
        val id: String,
        val source: String,
        val name: String,
        val client: TermsClient
) {


    companion object {
        const val KEY_EXTRA_OFFER = "EXTRA_OFFER"
        const val KEY_INTENT_OFFER_ID = "offerId"
        const val KEY_INTENT_OFFER_NAME = "offerName"
        const val KEY_INTENT_OFFER_SOURCE = "offerSource"
        const val KEY_INTENT_VIN = "systemVin"
        const val KEY_INTENT_OFFER_TERMS_TYPE = "termsType"
        const val KEY_INTENT_TERMS_SUMMARY = "termsSummary"
        const val KEY_INTENT_TERMS_EXTENDED = "termsExtended" // TODO("Delete")
        const val KEY_INTENT_TERMS_REQUIRED = "termsRequired"
        const val KEY_INTENT_TERMS = "termsRequired"
        const val KEY_INTENT_TERMS_CLIENT = "termsClient"
        const val VALUE_OFFER_SOURCE_ATT = "AT&T"
        const val KEY_INTENT_OFFER_ATT = "offerAtt"
    }
}