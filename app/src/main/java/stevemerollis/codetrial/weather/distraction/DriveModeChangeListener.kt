package stevemerollis.codetrial.weather.distraction

import android.car.Car
import android.car.drivingstate.CarDrivingStateEvent.*
import android.car.drivingstate.CarDrivingStateManager
import android.util.Log
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_DRIVE
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_IDLE
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_PARK
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_DRIVE
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_IDLE
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_PARK
import stevemerollis.codetrial.weather.util.PCarBinderUtil


class DriveModeChangeListener {

    private var drivingStateManager: CarDrivingStateManager? = null

    fun registerListener() {
        try {
            drivingStateManager = PCarBinderUtil.car?.getCarManager(Car.CAR_DRIVING_STATE_SERVICE) as CarDrivingStateManager

            //Sets the current drive mode of the vehicle
            drivingStateManager?.currentCarDrivingState?.eventValue?.let { onDriveModeChange(it) }

            drivingStateManager?.registerListener {
                onDriveModeChange(it.eventValue)
            }

            Log.i(javaClass.simpleName, "registered to drive mode change listener")
        } catch (e: Exception) {
            onDriveModeChange(DRIVING_STATE_UNKNOWN)
            Log.e(javaClass.simpleName, "error registering to drive mode change listener",e)
        }
    }

    private fun onDriveModeChange(curMode: Int) {
        Log.i(javaClass.simpleName, "DriveMode: $curMode")
        when (curMode) {
            DRIVING_STATE_PARKED -> setDriveMode(DD_PENDING_PARK, DRIVE_MODE_PARK) //Car is parked - Gear is in Parked mode.
            DRIVING_STATE_IDLING -> setDriveMode(DD_PENDING_IDLE, DRIVE_MODE_IDLE) //Car is idling.  Gear is not in Parked mode and Speed of the vehicle is zero.
            DRIVING_STATE_MOVING -> setDriveMode(DD_PENDING_DRIVE, DRIVE_MODE_DRIVE) //Car is moving.  Gear is not in parked mode and speed of the vehicle is non zero.
            else -> setDriveMode(DD_PENDING_DRIVE, DRIVE_MODE_DRIVE)
        }
    }

    /**
     * @pendingState was used for nav user to @TargetClass in com.gm.mybrand.driverdistraction
     * when @registerDriverDistraction was called
     *
     * Use case: When DD occurs in the Error Activity user should remains in that activity until
     * they finish it. @pendingState will be used when @onResume was called in the next activity,
     * user will be navigated to @TargetClass
     */
    private fun setDriveMode(@DdTarget pendingState: Int,
                             @DriverMode driveMode: Int) {

        DriverDistraction.setPendingState(pendingState)
        DriverDistraction.setCurrentDriveMode(driveMode)
        MyBrandDriveModeManager.getDriveModeObserver().onNext(driveMode)

    }
}