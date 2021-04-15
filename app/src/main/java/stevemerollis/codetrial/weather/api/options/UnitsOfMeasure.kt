package stevemerollis.codetrial.weather.api.options

sealed class UnitsOfMeasure {

    abstract val tempUnits: TempUnits

    abstract val speedUnits: SpeedUnits

    object Standard: UnitsOfMeasure() {
        override val speedUnits: SpeedUnits = SpeedUnits.MetersPerSecond
        override val tempUnits: TempUnits = TempUnits.Kelvin
    }

    object Metric: UnitsOfMeasure() {
        override val speedUnits: SpeedUnits = SpeedUnits.MetersPerSecond
        override val tempUnits: TempUnits = TempUnits.Celsius
    }

    object Imperial: UnitsOfMeasure() {
        override val speedUnits: SpeedUnits = SpeedUnits.MilesPerHour
        override val tempUnits: TempUnits = TempUnits.Fahrenheit
    }
}