package stevemerollis.codetrial.weather.vehicle.cal

import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager
import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager.CalId
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton


class CalibrationUtilImpl
@Inject
constructor(
    private val calibrationManager: CalibrationManager
) : CalibrationUtil {


    override val papiUrl: String = runBlocking {
        calibrationManager.getString(CalId.BACKOFFICE_SERVER_NAME, DEFAULT_PAPI) + "/"
                .also { papiCache = it }}

    override suspend fun getString(key: String): String? {
        return "https://${calibrationManager.getString(key, "")}"
    }

    companion object {
        const val DEFAULT_PAPI: String = "https://lab1.api.gm.com:20445"
        const val KEY_PAPI_BASE_URL: String = "https://idt3.papi.gm.com/"
        private var papiCache = ""
        private var vin = ""
    }
}