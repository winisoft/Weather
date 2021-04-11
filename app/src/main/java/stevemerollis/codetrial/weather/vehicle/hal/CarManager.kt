package stevemerollis.codetrial.weather.vehicle.hal

//import android.car.Car
//import android.car.drivingstate.CarDrivingStateManager
//import android.car.hardware.property.CarPropertyManager
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import stevemerollis.codetrial.weather.vehicle.hal.Car.CarDrivingStateManager
import stevemerollis.codetrial.weather.vehicle.hal.Car.CarPropertyManager


class CarManager
@Inject
constructor(
    @ApplicationContext private val context: Context
) : ServiceConnection,
        CoroutineScope by CoroutineScope(CoroutineDsl.bg + Job()) {

    private var car: Car = Car.createCar(context, this).also { it.connect() }
    private val _carProps: MutableStateFlow<Car.CarPropertyManager?> = MutableStateFlow(null)
    private val _driveState: MutableStateFlow<Int?> = MutableStateFlow(-1)
    val carProps: StateFlow<Car.CarPropertyManager?> = _carProps
    val driveState: StateFlow<Int?> = _driveState

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        logI { "$TAG: onServiceConnected: Car is connected!" }
        _carProps.value = car.getCarManager(Car.PROPERTY_SERVICE) as? CarPropertyManager
        logI { "$TAG: built car property manager: ${_carProps.value}" }
        _driveState.value = (car.getCarManager(Car.CAR_DRIVING_STATE_SERVICE) as CarDrivingStateManager)
                .currentCarDrivingState?.eventValue
        logI { "$TAG: initial car driving state: ${_driveState.value}" }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        logI { "$TAG: onServiceDisconnected: Car is disconnected" } //no-op
    }



    companion object {
        val TAG: String = CarManager::class.simpleName.toString()
    }
}