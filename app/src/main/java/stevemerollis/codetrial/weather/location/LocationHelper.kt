package stevemerollis.codetrial.weather.location

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LocationHelper @Inject constructor() : ILocationHelper {

    @SuppressLint("MissingPermission")
    override fun getLastLocation(activity: Activity): Task<Location?> {
        return LocationServices.getFusedLocationProviderClient(activity).lastLocation
    }
}