package stevemerollis.codetrial.weather.async

import stevemerollis.codetrial.weather.R
import java.io.IOException
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import stevemerollis.codetrial.weather.host.HostActivity

sealed class ViewModelState<out T> {


    data class Success<out T>(val data: T): ViewModelState<T>() {
        override fun toString(): String = "Success"
    }

    sealed class Error(
            val message: String? = null,
            val throwable: Throwable? = null
    ): ViewModelState<Nothing>(), Parcelable {

        abstract val titleRes: Int
        abstract val contentRes: Int
        abstract val actionTextRes: Int
        abstract val secondaryTextRes: Int?
        abstract val primaryAction: Function<Any?>
        abstract val secondaryAction: Function<Any?>?

        /**
         * An error caused by any failure not provided by the back office, and not a failure of
         * the network transmission
         */
        @Parcelize
        object Generic: Error(), Parcelable {

            override val titleRes: Int get() = R.string.mybrand_10_5_1_purchaseFail_csm

            override val contentRes: Int get() = R.string.error_generic_message_quote

            override val actionTextRes: Int get() = R.string.quit

            override val secondaryTextRes: Int get() = R.string.mybrand_10_0_callAdvisor_csm

            override val primaryAction: () -> Any? get() = ::action as () -> Any?

            override val secondaryAction: () -> Any? get() = ::secondaryAction

            override fun toString(): String = "Generic Error"

            fun action(hostActivity: HostActivity) {
                hostActivity.finish()
            }

            fun secondaryAction(hostActivity: HostActivity) {

            }
        }

        /**
         * An exception occurred during the call itself -- most likely an IO exception or timeout
         */
        @Parcelize
        object Technical: Error(), Parcelable {

            override val titleRes: Int
                get() = R.string.error_technical_title

            override val contentRes: Int
                get() = R.string.error_technical_message

            override val actionTextRes: Int
                get() = R.string.okLabel

            override val secondaryTextRes: Int?
                get() = null

            override val primaryAction: () -> Any? get() = { }

            override val secondaryAction: () -> Any? get() = { }

            override fun toString(): String = "Technical Error"
        }

        fun toModel(): Error = when(this) {
            is Generic -> Generic
            is Technical -> Technical
        }
    }

    companion object {

        fun from(result: AsyncResult.Error): ViewModelState<Nothing> {
            return when(result) {
                is AsyncResult.Error.Technical -> Error.Technical
                else -> Error.Generic
            }
        }

        fun from(throwable: Throwable): Error {
            return when(throwable){
                is IOException -> Error.Technical
                else -> Error.Generic
            }
        }
    }
}