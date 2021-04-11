package stevemerollis.codetrial.weather.vehicle.hal

import stevemerollis.codetrial.weather.vehicle.hal.Car.CarPropertyValue


interface HardwareLayer {

    suspend fun <T: Any> getProperty(
            id: Int,
            area: Int
    ): CarPropertyValue<T>
}