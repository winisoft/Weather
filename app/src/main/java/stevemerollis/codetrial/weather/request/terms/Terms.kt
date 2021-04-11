package stevemerollis.codetrial.weather.request.terms

import android.os.Parcelable
import android.text.Spanned
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.text.SimpleDateFormat
import java.util.*


@Parcelize
data class Terms(
        val summary: String = "",
        val extended: @RawValue Spanned? = null,
        val isAcceptRequired: Boolean = false,
        val termsClient: TermsClient
): Parcelable {

    companion object {
        fun roscaSummary(amount: String, creditCard: String): String = """
By clicking "Place Order" below, you authorize OnStar to process recurring payments in the amount of $amount, 
using credit card ending in $creditCard on ${SimpleDateFormat("MM/dd/yyy", Locale.getDefault()).format(Date())} 
to purchase the above identified services, under the User Terms 
for Connected Vehicle Services available at: https://www.onstar.com/us/en/footer-links/terms-conditions.html.
The amount and frequency of each recurring payment is based upon the service[s] and payment interval[s] you select. 
You may cancel at any time by pressing the blue OnStar button in your vehicle or by calling us at 1.888.466.7827
    """.trimIndent()
    }
}