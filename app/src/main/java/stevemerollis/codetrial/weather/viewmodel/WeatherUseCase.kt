package stevemerollis.codetrial.weather.viewmodel

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
abstract class WeatherUseCase<T>
constructor(
    open val coroutineScope: CoroutineScope
) : UseCase<T> {

    fun mutate(intention: UseCase.Intention): Flow<UseCase.Result<T>> {
        return intentChannel.apply { offer(intention) }.run { resultFlow }
    }

    override val intentChannel: Channel<UseCase.Intention> = Channel(UNLIMITED)

    override val resultFlow: Flow<UseCase.Result<T>>
        get() = intentChannel
        .consumeAsFlow()
        .transform {
            emit(getResult(it))
        }

}