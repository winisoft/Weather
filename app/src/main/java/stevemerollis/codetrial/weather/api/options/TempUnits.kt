package stevemerollis.codetrial.weather.api.options

sealed class TempUnits {

    object Kelvin: TempUnits()

    object Celsius: TempUnits()

    object Fahrenheit: TempUnits()
}