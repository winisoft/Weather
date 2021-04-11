package stevemerollis.codetrial.weather.vehicle.hal.vin

import android.annotation.SuppressLint
import android.content.Context
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.vehicle.hal.HardwareLayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import stevemerollis.codetrial.weather.vehicle.hal.Car.CarPropertyValue
import stevemerollis.codetrial.weather.vehicle.hal.Car.CarPropertyManager


class VinUtilImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val vehicleSignals: HardwareLayer
): VinUtil,
CoroutineScope by CoroutineScope(CoroutineDsl.bg + Job()) {

    var carPropertyManager: CarPropertyManager? = null
    private val _vinState: MutableStateFlow<String?> = MutableStateFlow(null)
    override val vinState: StateFlow<String?> = _vinState

    private suspend fun getVin(): String = withContext(CoroutineDsl.bg) {
        logD { "$TAG: Fetching vin..." }
        vehicleSignals.getProperty<String>(INFO_VIN, INFO_VIN_AREA).let { prop ->
            if (prop.status == CarPropertyValue.STATUS_AVAILABLE) {
                logD { "$TAG: Retrieved vin ${prop.value} from vehicleSignals HAL..." }
                prop.value
                        .also { logD{ "found a vin! applying to MutieStateFlow" }; _vinState.value = it }
            } else {
                logD { "$TAG: Failed to fetch vin... status: ${prop.status}" }
                getSystemProp(context)
                        .also { _vinState.value = it }
            }
        }
    }

    @SuppressLint("PrivateApi")
    private fun getSystemProp(context: Context): String {
        logI { "$TAG Falling back: Trying to get VIN form systemProperties persist.auth.vin" }
        return try {
            context.classLoader.let { systemProperties ->
                systemProperties.loadClass("android.os.SystemProperties")
                        .getMethod("get", *arrayOf<Class<*>>(String::class.java))
                        .invoke(systemProperties, *arrayOf<Any>("persist.auth.vin")) as String
            }
        } catch (e: java.lang.Exception) {
            logE { "$TAG Unable to get value from persist.auth.vin" }
            ""
        }
    }

    companion object {
        val TAG: String = VinUtilImpl::class.simpleName.toString()
        const val INFO_VIN = 554696960
        const val INFO_VIN_AREA = 0
    }

}