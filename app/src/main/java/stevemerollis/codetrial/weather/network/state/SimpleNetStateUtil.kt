package stevemerollis.codetrial.weather.network.state

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import stevemerollis.codetrial.weather.network.helper.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton


class SimpleNetStateUtil
@Inject
constructor(
        @ApplicationContext private val connectivityManager: ConnectivityManager,
        private val coroutineScope: CoroutineScope
): NetStateUtil {

    suspend fun <T: Any> Flow<NetworkResult<T>>.emitNoNetworkOrProceed(): Flow<NetworkResult<*>> = transform {
        if (isNetworkAvailable(connectivityManager, coroutineScope).value)
            emit(it)
        else
            emit(NetworkResult.Error.Unavailable)
    }

    private val netTransports: List<Int> = listOf(TRANSPORT_WIFI, TRANSPORT_CELLULAR, TRANSPORT_ETHERNET)

    override suspend fun isNetworkAvailable(
        connectivityManager: ConnectivityManager,
        coroutineScope: CoroutineScope
    ): StateFlow<Boolean> = flow {
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