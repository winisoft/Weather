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

            override val rTitle: Int get() = R.string.mybrand_10_5_1_purchaseFail_csm

            override val rMessage: Int get() = R.string.error_generic_message_quote

            override val rActionText: Int get() = R.string.quit

            override val rActionTextSecondary: Int get() = R.string.mybrand_10_0_callAdvisor_csm

            override val action: () -> Any? get() = ::execute as () -> Any?

            override val actionSecondary: () -> Any? get() = ::actionSecondary

            override fun toString(): String = "Generic Error"

            fun execute(hostActivity: HostActivity) {
                hostActivity.finish()
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
                get() = R.string.error_technical_title

            override val rMessage: Int
                get() = R.string.error_technical_message

            override val rActionText: Int
                get() = R.string.okLabel

            override val rActionTextSecondary: Int?
                get() = null

            override val action: () -> Any? get() = { }

            override val actionSecondary: () -> Any? get() = { }

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