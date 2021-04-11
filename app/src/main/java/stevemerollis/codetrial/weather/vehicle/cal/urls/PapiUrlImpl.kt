package stevemerollis.codetrial.weather.vehicle.cal.urls

import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager
import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager.CalId
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton
import javax.inject.Inject


class PapiUrlImpl
@Inject
constructor(
    cal: CalibrationManager
): PapiUrl {

    override val papiBaseUrl: String =
            cal.getString(CalId.BACKOFFICE_SERVER_NAME, "https://lab1.api.gm.com:20445").run {
                if (startsWith("http") || startsWith("https"))
                    this
                else
                    "https://${substringAfter("//")}"
            }

}