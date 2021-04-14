package stevemerollis.codetrial.weather.network.state


import android.net.ConnectivityManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow


interface NetStateUtil {

    suspend fun isNetworkAvailable(): StateFlow<Boolean>

}