package stevemerollis.codetrial.weather.network.api

sealed class TempUnits {

    object Kelvin: TempUnits()

    object Celsius: TempUnits()

    object Fahrenheit: TempUnits()
}