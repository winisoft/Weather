package stevemerollis.codetrial.weather.distraction

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject.create


object MyBrandDriveModeManager {
    private val driveModeSubject = create<@DriverMode Int>()

    fun getDriveModeObservable(): Observable<@DriverMode Int> {
        return driveModeSubject
    }

    fun getDriveModeObserver(): Observer<@DriverMode Int> {
        return driveModeSubject
    }
}