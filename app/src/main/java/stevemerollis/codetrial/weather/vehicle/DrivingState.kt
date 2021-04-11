package stevemerollis.codetrial.weather.vehicle


sealed class DrivingState(open val value: Int) {
    object Unknown: DrivingState(-1) {
        override val value: Int = -1
        override fun toString(): String = "Unknown"
    }
    object Parked: DrivingState(0) {
        override val value: Int = 0
        override fun toString(): String = "Parked"
    }
    object Idling: DrivingState(1) {
        override val value: Int = 1
        override fun toString(): String = "Idling"
    }
    object Moving: DrivingState(2) {
        override val value: Int = 2
        override fun toString(): String = "Moving"
    }
}