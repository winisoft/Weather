package stevemerollis.codetrial.weather.lifetime

import android.content.Intent
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.request.OfferModel

class PurchaseModel {

    val intent: Intent? = null

    var vin: String? = null

    var offerModel: OfferModel? = null

    var otpTarget: CodeRequestBody? = null
}