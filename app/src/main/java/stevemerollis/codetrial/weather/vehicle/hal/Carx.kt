package stevemerollis.codetrial.weather.vehicle.hal

import android.content.Context
import android.content.ServiceConnection

//Car.createCar(it.connect()CarPropertyManager
//import android.car.Car
//import android.car.drivingstate.CarDrivingStateManager
//import android.car.hardware.property.CarPropertyManager

class Car(){

    constructor( context: Context, con: ServiceConnection): this() {}

    fun connect() {

    }

    fun getCarManager(service: String): CarSuperManager = CarPropertyManager()

    companion object {
        fun createCar( context: Context, con: ServiceConnection): Car = Car()

        const val PROPERTY_SERVICE = ""
        const val CAR_DRIVING_STATE_SERVICE = ""
    }

    open class CarSuperManager {

    }

    class CarDrivingStateManager: CarSuperManager() {
        val currentCarDrivingState: CurrentCarDrivingState? = null
    }
    class CurrentCarDrivingState {
        val eventValue: Int = 0
    }
    class CarPropertyManager: CarSuperManager() {

    }

    class CarPropertyValue<T> where T: Any {
        val status: String = "0"
        val value: String = "0"
        companion object {
            const val STATUS_AVAILABLE = "0"
        }
    }
}