package stevemerollis.codetrial.weather.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalCoroutinesApi::class)
interface UseCase<T> {

    val intentChannel: Channel<Intention>

    val resultFlow: Flow<Result<T>>

    suspend fun getResult(intention: Intention): Result<T>

    interface Intention

    interface Result<T>

}