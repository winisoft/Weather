package stevemerollis.codetrial.weather.permissions

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject


object PermissionSignal {
    private val signalSubject = PublishSubject.create<PermissionData>()

    fun getEventObservable(): Observable<PermissionData> {
        return signalSubject
    }

    fun getEventObserver(): Observer<PermissionData> {
        return signalSubject
    }
}