package stevemerollis.codetrial.weather.util

import stevemerollis.codetrial.weather.async.AsyncResult
import shark.SharkLog
import timber.log.Timber.*
import java.util.logging.Logger


object lo {

    operator fun Unit.unaryPlus() {

    }
    fun ok() {

    }
    fun logV(message: () -> String) = tag("Payment").v(message())

    fun logV(throwable: Throwable?, message: () -> String): Unit = tag("Payment").v(throwable,
            "${throwable?.message}\n${message()}")

    fun logD(message: () -> String) = tag("Payment").d(message())

    fun logD(throwable: Throwable?, message: () -> String): Unit = tag("Payment").d(throwable,
            "${throwable?.message}\n${message()}")

    fun logI(message: () -> String) = tag("Payment").i(message())

    fun logI(throwable: Throwable?, message: () -> String): Unit = tag("Payment").i(throwable,
            "${throwable?.message}\n${message()}")

    fun logW(message: () -> String) = tag("Payment").w(message())

    fun logW(throwable: Throwable?, message: () -> String): Unit = tag("Payment").w(throwable,
            "${throwable?.message}\n${message()}")

    fun logE(message: () -> String) = tag("Payment").e(message())

    fun logE(throwable: Throwable?, message: () -> String): Unit = tag("Payment").e(throwable,
            "${throwable?.message}\n${message()}")

    fun logE(error: AsyncResult.Error?, message: () -> String) {
        if (error?.throwable != null && error.message != null)
            logE(error.throwable) { error.message }
        if (error?.throwable == null && error?.message != null)
            logE { error.message }
        message().let{ if (it.isNotEmpty()) logE { it } }
    }

    fun ifThisLogThat(block: List<Pair<() -> Boolean, String>>.() -> Unit): String {
        return emptyList<Pair<() -> Boolean, String>>()
                .apply(block)
                .filter { it.first() }.run {
                    val sb = StringBuilder()
                    forEach { sb.append(it) }
                }.toString()
    }

}