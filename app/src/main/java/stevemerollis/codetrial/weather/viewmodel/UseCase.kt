package stevemerollis.codetrial.weather.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalCoroutinesApi::class)
interface UseCase<T> {


    suspend operator fun invoke(scope: CoroutineScope): Job

    val resultFlow: Flow<Result<T>>

    interface Result<T> {
        data class Success<T>(val model: T): Result<T>
        data class Error(val title: Int, val message: Int): Result<Nothing>
    }

}