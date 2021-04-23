package stevemerollis.codetrial.weather.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.main.MainActivity

/**
 * Presumes the need for an error presentation with a title, message, and one or two possible actions
 */
interface CurrentlyState: State {
    object Loading: CurrentlyState
    sealed class Error(
        val message: String? = null,
        val throwable: Throwable? = null
    ): CurrentlyState, Parcelable {

        abstract val rTitle: Int
        abstract val rMessage: Int
        abstract val rActionText: Int
        abstract val rActionTextSecondary: Int?
        abstract val action: Function<Any?>
        abstract val actionSecondary: Function<Any?>?

        /**
         * An error caused by any failure not provided by the back office, and not a failure of
         * the network transmission
         */
        @Parcelize
        object Generic: Error(), Parcelable {

            override val rTitle: Int get() = R.string.title

            override val rMessage: Int get() = R.string.message

            override val rActionText: Int get() = R.string.actionText

            override val rActionTextSecondary: Int get() = R.string.actionTextSecondary

            override val action: () -> Any? get() = ::execute as () -> Any?

            override val actionSecondary: () -> Any? get() = ::actionSecondary

            override fun toString(): String = "Generic Error"

            fun execute(mainActivity: MainActivity) {
                mainActivity.finish()
            }

            fun secondaryAction() {

            }
        }

        /**
         * An exception occurred during the call itself -- most likely an IO exception or timeout
         */
        @Parcelize
        object Technical: Error(), Parcelable {

            override val rTitle: Int
                get() = R.string.title

            override val rMessage: Int
                get() = R.string.message

            override val rActionText: Int
                get() = R.string.actionText

            override val rActionTextSecondary: Int?
                get() = null

            override val action: () -> Any? get() = { }

            override val actionSecondary: () -> Any? get() = { }

            override fun toString(): String = "Technical Error"
        }
    }
}