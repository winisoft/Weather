package stevemerollis.codetrial.weather.paymethods

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class PayMethod(val id: String, val lastFour: String, val type: CardType): Parcelable {

    sealed class CardType(open val value: String): Parcelable {

        @Parcelize object Visa: CardType("visa")

        @Parcelize object MasterCard: CardType("mastercard")

        @Parcelize object AmericanExpress: CardType("americanexpress")

        @Parcelize object Discover: CardType("discover")

        @Parcelize object Unknown: CardType("")

        companion object {
            fun getById(type: String?): CardType = when (type?.toLowerCase(Locale.ROOT) ?: "") {
                Visa.value -> Visa
                MasterCard.value -> MasterCard
                AmericanExpress.value -> AmericanExpress
                Discover.value -> Discover
                else -> Unknown
            }
        }
    }
}

