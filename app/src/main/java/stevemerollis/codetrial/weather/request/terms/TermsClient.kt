package stevemerollis.codetrial.weather.request.terms

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize




sealed class TermsClient(val id: Int): Parcelable {

    @Parcelize object None: TermsClient(-1)

    @Parcelize object ATT: TermsClient(0)

    @Parcelize object OnStar: TermsClient(1)

    @Parcelize object Custom: TermsClient(2)

    companion object {
        fun from(value: Int): TermsClient = when(value) {
            0 -> ATT
            1 -> OnStar
            2 -> Custom
            else -> None
        }
    }

}