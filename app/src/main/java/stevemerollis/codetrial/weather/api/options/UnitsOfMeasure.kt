package stevemerollis.codetrial.weather.api.options

import java.io.InputStream

sealed class UnitsOfMeasure(val key: String) {

    abstract val tempUnits: TempUnits

    abstract val speedUnits: SpeedUnits

    object Standard: UnitsOfMeasure("standard") {
        override val speedUnits: SpeedUnits = SpeedUnits.MetersPerSecond
        override val tempUnits: TempUnits = TempUnits.Kelvin
    }

    object Metric: UnitsOfMeasure("metric") {
        override val speedUnits: SpeedUnits = SpeedUnits.MetersPerSecond
        override val tempUnits: TempUnits = TempUnits.Celsius
    }

    object Imperial: UnitsOfMeasure("imperial") {
        override val speedUnits: SpeedUnits = SpeedUnits.MilesPerHour
        override val tempUnits: TempUnits = TempUnits.Fahrenheit
    }

    companion object {
        fun fromKey(key: String): UnitsOfMeasure = when(key) {
            Metric.key -> Metric
            Imperial.key -> Imperial
            else -> Standard
        }
    }
}