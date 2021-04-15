package stevemerollis.codetrial.weather.currently.app

import dispatch.core.DispatcherProvider
import dispatch.core.withIO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.network.helper.NetworkResult
import stevemerollis.codetrial.weather.util.lo.logD
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class CurrentlyRepositoryImpl
@Inject
constructor(
    private val networkHelper: NetworkHelper
) : CurrentlyRepository {

//    @ExperimentalCoroutinesApi
//    private val changesChannel = BroadcastChannel<>(Channel.CONFLATED)

    private suspend fun getCurrentWeatherFromRemote(): Flow<NetworkResult<CurrentlyResponse>> =
        withIO {
            networkHelper.getCurrentWeather()
        }

    override suspend fun getCurrentWeather(): Flow<NetworkResult<CurrentlyResponse>> {

        return getCurrentWeatherFromRemote()

//        changesChannel.asFlow()
//            .onEach {
//                logD { "$TAG: Change=$it" }
//            }.scan(initial) { acc, change ->
//                when (change) {
//                    is Change.Fetched -> change.response
//                }
//            }.onEach {
//                logD { "$TAG emit currentlyResponse: $it" }
//            }.let {
//                emitAll(it)
//            }
    }

    override suspend fun refresh() {
        getCurrentWeatherFromRemote()} //.let { changesChannel.send(Change.Fetched(it)) }

    companion object {
        val TAG: String = CurrentlyRepositoryImpl::class.simpleName.toString()
    }

}