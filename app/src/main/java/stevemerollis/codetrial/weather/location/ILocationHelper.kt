package stevemerollis.codetrial.weather.location

import android.app.Activity
import android.location.Location
import com.google.android.gms.tasks.Task

interface ILocationHelper {
    fun getLastLocation(activity: Activity): Task<Location?>
}