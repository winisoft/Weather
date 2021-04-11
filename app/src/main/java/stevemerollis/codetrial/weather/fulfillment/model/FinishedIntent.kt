package stevemerollis.codetrial.weather.fulfillment.model

import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class FinishedIntent(reason: Reason): Intent() {

    sealed class Reason(val value: Int) : Parcelable {

        @Parcelize
        object Unknown: Reason(0)

        @Parcelize
        object Success: Reason(1)

        @Parcelize
        object InvalidArgs: Reason(2)

        @Parcelize
        object GenericError: Reason(3)

    }

    init {
        action = "ACTION_GM_PAYMENT_COMPLETED"
        extras?.putInt("COMPLETE_REASON", reason.value)
    }

    companion object {
        val TAG: String = FinishedIntent::class.simpleName.toString()
    }
}

