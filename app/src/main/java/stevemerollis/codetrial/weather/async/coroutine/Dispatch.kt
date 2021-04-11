package stevemerollis.codetrial.weather.async.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface Dispatch {

    val io: CoroutineDispatcher

    val ui: CoroutineDispatcher

    val bg: CoroutineDispatcher

}