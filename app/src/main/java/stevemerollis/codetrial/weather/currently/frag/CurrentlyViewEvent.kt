package stevemerollis.codetrial.weather.currently.frag

import android.location.Location

sealed class CurrentlyViewEvent {

    class Initial(location: Location?): CurrentlyViewEvent()

    object Refresh: CurrentlyViewEvent()
}