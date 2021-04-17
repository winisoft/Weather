package stevemerollis.codetrial.weather.network.state

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.network.call.NetworkResult
import javax.inject.Inject


class SimpleNetStateUtil
@Inject
constructor(
        @ApplicationContext private val context: Context,
        private val coroutineScope: CoroutineScope
): NetStateUtil {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun <T: Any> Flow<NetworkResult<T>>.emitNoNetworkOrProceed(): Flow<NetworkResult<*>> = transform {
        if (isNetworkAvailable().value)
            emit(it)
        else
            emit(NetworkResult.Error.Unavailable)
    }

    private val netTransports: List<Int> = listOf(TRANSPORT_WIFI, TRANSPORT_CELLULAR, TRANSPORT_ETHERNET)

    override suspend fun isNetworkAvailable(): StateFlow<Boolean> = flow {
        connectivityManager.apply {
            val capabilities = activeNetwork?.let { getNetworkCapabilities(it) }
            netTransports
                .any {
                    capabilities?.hasTransport(it) ?: false
                }.apply {
                    emit(this)
                }
        }
    }.stateIn(coroutineScope)
}