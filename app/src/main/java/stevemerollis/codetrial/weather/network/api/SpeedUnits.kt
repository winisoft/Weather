package stevemerollis.codetrial.weather.network.api

sealed class SpeedUnits {

    object MetersPerSecond: SpeedUnits()

    object MilesPerHour: SpeedUnits()
}