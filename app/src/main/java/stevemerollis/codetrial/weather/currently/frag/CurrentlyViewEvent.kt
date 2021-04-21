package stevemerollis.codetrial.weather.currently.frag

sealed class CurrentlyViewEvent {

    object Initial: CurrentlyViewEvent()

    object Refresh: CurrentlyViewEvent()
}