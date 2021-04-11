package stevemerollis.codetrial.weather.distraction

import androidx.appcompat.app.AppCompatActivity
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_DRIVE
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_PARK
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_DRIVE
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_NULL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.toBehaviorSubject


/**
 * DriverDistraction class can subscribe to the DD observer to receive DD changed event from @DriveModeChangeListener class
 * Send DDListener to @registerDriverDistraction to handle the driver distraction callback
 * Note: Call registerDriverDistraction from the onResume method, unregister from onPause Method
 *
 * @pendingState indicates the DD state happens between the view onCreate and registerDriverDistraction
 * @targetClass is the class we should nav to when DD kicks in
 */
class DriverDistraction constructor(driveModeObservableArg: Observable<Int>) {
    constructor() : this(MyBrandDriveModeManager.getDriveModeObservable())

    val driveModeObservable = driveModeObservableArg
            .startWith(DRIVE_MODE_PARK)
            // This guarantees that the source observable does not complete,
            // which would cause it to ignore the values after.
            .mergeWith(Observable.never())
            .toBehaviorSubject()

    companion object {
        var targetClass: Class<out AppCompatActivity>? = null

        @DdTarget
        private var pendingState = DD_PENDING_NULL

        private var currentDriveMode: Int = DRIVE_MODE_PARK
        private var driveModeSubscriber: Disposable? = null

        fun setPendingState(@DdTarget state: Int) {
            pendingState = state
        }

        fun getPendingState(): @DdTarget Int {
            return pendingState
        }

        fun getCurrentDriveMode(): @DriverMode Int {
            return currentDriveMode
        }

        fun setCurrentDriveMode(@DriverMode driveMode: Int) {
            currentDriveMode = driveMode
        }

        fun isInDriveMode(): Boolean {
            //Drive mode is active unless parking mode.
            return currentDriveMode != DRIVE_MODE_PARK
        }

        fun isInRsaDriveMode(): Boolean {
            //Road Side Assistance Drive mode is active if speed is greater than 5.
            return currentDriveMode == DRIVE_MODE_DRIVE
        }

        fun registerDriverDistraction(currentClazz: Class<out AppCompatActivity>,
                                      listener: DriverDistractionListener) {

            //Nav to the target activity, it will register listener at onResume
            //Road side assistance should be accessible when parking mode & idling mode hence we send callback as drive mode active it is handled
            //in the code when callback received.
            if ((getPendingState() == DD_PENDING_DRIVE)
                    && currentClazz != targetClass) {
                setPendingState(DD_PENDING_NULL)
                listener.onDriverDistractionActive(targetClass)

                return
            }

            unRegisterDriverDistraction()

            driveModeSubscriber = MyBrandDriveModeManager.getDriveModeObservable()
                    .subscribeOn(mainThread())
                    .observeOn(mainThread())
                    .subscribe {
                        //Road side assistance only accessible while car is in PARK & idling mode hence we send callback as drive mode active it is handled
                        //in the code when callback received.
                        if (currentDriveMode != DriveMode.DRIVE_MODE_PARK) {
                            listener.onDriverDistractionActive(if (targetClass == currentClazz) null else targetClass)
                        } else {
                            listener.onDriverDistractionInactive()
                        }

                        setPendingState(DD_PENDING_NULL)
                    }
        }

        fun unRegisterDriverDistraction() {
            driveModeSubscriber?.dispose()
        }
    }
}
