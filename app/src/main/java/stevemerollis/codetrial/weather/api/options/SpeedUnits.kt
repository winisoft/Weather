package stevemerollis.codetrial.weather.api.options

sealed class SpeedUnits {

    object MetersPerSecond: SpeedUnits()

    object MilesPerHour: SpeedUnits()
}