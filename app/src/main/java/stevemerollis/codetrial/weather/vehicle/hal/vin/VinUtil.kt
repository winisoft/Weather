package stevemerollis.codetrial.weather.vehicle.hal.vin

import stevemerollis.codetrial.weather.async.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface VinUtil {

    val vinState: StateFlow<String?>

}