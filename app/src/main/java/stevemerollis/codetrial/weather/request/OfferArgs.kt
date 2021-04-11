package stevemerollis.codetrial.weather.request

import android.os.Parcelable
import stevemerollis.codetrial.weather.request.terms.TermsClient
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OfferArgs(
        val id: String,
        val source: String,
        val name: String,
        val termsType: TermsClient
): Parcelable