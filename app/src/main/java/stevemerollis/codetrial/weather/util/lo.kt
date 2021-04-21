package stevemerollis.codetrial.weather.util

import stevemerollis.codetrial.weather.async.AsyncResult
import shark.SharkLog
import timber.log.Timber.*
import java.util.logging.Logger


object lo {

    fun logV(message: () -> String) = tag("Weather").v(message())

    fun logV(throwable: Throwable?, message: () -> String): Unit = tag("Weather").v(throwable,
            "${throwable?.message}\n${message()}")

    fun logD(message: () -> String) = tag("Weather").d(message())

    fun logD(throwable: Throwable?, message: () -> String): Unit = tag("Weather").d(throwable,
            "${throwable?.message}\n${message()}")

    fun logI(message: () -> String) = tag("Weather").i(message())

    fun logI(throwable: Throwable?, message: () -> String): Unit = tag("Weather").i(throwable,
            "${throwable?.message}\n${message()}")

    fun logW(message: () -> String) = tag("Weather").w(message())

    fun logW(throwable: Throwable?, message: () -> String): Unit = tag("Weather").w(throwable,
            "${throwable?.message}\n${message()}")

    fun logE(message: () -> String) = tag("Weather").e(message())

    fun logE(throwable: Throwable?, message: () -> String): Unit = tag("Weather").e(throwable,
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