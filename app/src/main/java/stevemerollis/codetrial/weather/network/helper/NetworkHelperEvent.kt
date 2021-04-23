package stevemerollis.codetrial.weather.network.helper

sealed class NetworkHelperEvent {

    object GetCurrentWeather: NetworkHelperEvent()
}