package stevemerollis.codetrial.weather.async.tools

import stevemerollis.codetrial.weather.util.lo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

object Retrying {

    /**
     * Retries the [executing] block according to specified conditions
     *
     * @param T The type of the flow this operates on
     * @param maxAttemptCount The number of times to retry
     * @param initialDelayMillis The initial delay between attempts in milliseconds
     * @param maxDelayMillis The maximum delay permissible in milliseconds
     * @param delta How much to multiply the delay value by after each successive retry
     * @param executing The suspending function to be retried
     * @param until The suspending function that must return true to terminate
     * @param tally A handy integer var that facilitates tracking some useful metric
     */
    suspend fun <T> Flow<T>.conditionalRetries(
            maxAttemptCount: Int = 10,
            initialDelayMillis: Long = 50L,
            maxDelayMillis: Long = 1000,
            delta: Double = 1.33,
            doThis: suspend () -> Flow<T>,
            until: suspend (Flow<T>) -> Boolean = { false },
    ): Flow<*> {

        var currentDelay = initialDelayMillis

        repeat(maxAttemptCount - 1) {
            this.apply {
                doThis()
                if (until(this)) {
                    lo.logV { "conditional retry: result passes verification" }
                    return this
                } else {
                    lo.logV { "conditional retry: Waited $currentDelay to try again... but failed." }
                    delay(currentDelay)
                    currentDelay = (currentDelay * delta).toLong().coerceAtMost(maxDelayMillis)
                }
            }
        }

        return doThis()
    }
}