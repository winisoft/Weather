package stevemerollis.codetrial.weather.currently.app

import dispatch.core.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.util.lo.logD
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class CurrentlyRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper,
    private val dispatchers: DispatcherProvider
) : CurrentlyRepository {

    private sealed class Change {
        data class Refreshed(val response: CurrentlyResponse): Change()
    }

    @ExperimentalCoroutinesApi
    private val changesChannel = BroadcastChannel<Change>(Channel.CONFLATED)

    private suspend fun getCurrentWeatherFromRemote(): CurrentlyResponse = withContext(dispatchers.io) {
        networkHelper.getCurrentWeather()
    }

    override fun getCurrentWeather(): Flow<CurrentlyResponse> = flow {
        val initial = getCurrentWeatherFromRemote()

        changesChannel
            .asFlow()
            .onEach {
                logD { "$TAG: Change=$it" }
            }.scan(initial) { acc, change ->
                when (change) {
                    is Change.Refreshed -> change.response
                }
            }.onEach {
                logD { "$TAG emit currentlyResponse: $it" }
            }.let {
                emitAll(it)
            }
    }

    override suspend fun refresh() =
        getCurrentWeatherFromRemote().let { changesChannel.send(Change.Refreshed(it)) }

    companion object {
        val TAG: String = CurrentlyRepositoryImpl::class.simpleName.toString()
    }

}