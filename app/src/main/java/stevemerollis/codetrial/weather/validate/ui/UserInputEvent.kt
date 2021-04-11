package stevemerollis.codetrial.weather.validate.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class UserInputEvent(val ordinal: Int, val value: String): Parcelable {
    @Parcelize object Zero: UserInputEvent(0, "0")
    @Parcelize object One: UserInputEvent(1, "1")
    @Parcelize object Two: UserInputEvent(2, "2")
    @Parcelize object Three: UserInputEvent(3, "3")
    @Parcelize object Four: UserInputEvent(4, "4")
    @Parcelize object Five: UserInputEvent(5, "5")
    @Parcelize object Six: UserInputEvent(6, "6")
    @Parcelize object Seven: UserInputEvent(7, "7")
    @Parcelize object Eight: UserInputEvent(8, "8")
    @Parcelize object Nine: UserInputEvent(9, "9")
    @Parcelize object Go: UserInputEvent(10, "Go")
    @Parcelize object Delete: UserInputEvent(11, "")
    @Parcelize object Resend: UserInputEvent(12, "Resend")

    companion object {
        fun from(value: String): UserInputEvent = when(value) {
            "0" -> Zero; "1" -> One; "2" -> Two; "3" -> Three; "4" -> Four; "5" -> Five
            "6" -> Six; "7" -> Seven; "8" -> Eight; "9" -> Nine; "Go" -> Go; "Resend" -> Resend;
            else -> Delete
        }
        fun getFromOrdinal(ordinal: Int): UserInputEvent = when(ordinal) {
            Zero.ordinal -> Zero
            One.ordinal -> One
            Two.ordinal -> Two
            Three.ordinal -> Three
            Four.ordinal -> Four
            Five.ordinal -> Five
            Six.ordinal -> Six
            Seven.ordinal -> Seven
            Eight.ordinal -> Eight
            Nine.ordinal -> Nine
            Go.ordinal -> Go
            Delete.ordinal -> Delete
            Resend.ordinal -> Resend
            else -> throw IllegalArgumentException("This is not even possible")
        }
    }
}