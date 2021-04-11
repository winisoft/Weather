package stevemerollis.codetrial.weather.util

import android.annotation.SuppressLint
import android.car.Car
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import stevemerollis.codetrial.weather.distraction.DriveModeChangeListener
import io.reactivex.Completable.complete
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/***
 * PcarBinder Util binds to get PCar and save the instance so that the instance can
 * be used across app to get required service. And also Once Pcar instance is available it
 * registers to drive-state change listener
 */
class PCarBinderUtil @Inject constructor() : ServiceConnection {

    private var onBindResult: (bindResult: Boolean) -> Unit = {}
    private var disposable: Disposable? = null

    @SuppressLint("CheckResult", "LogNotTimber")
    fun bind(context: Context, onBindResult: (bindResult: Boolean) -> Unit) {
        this.onBindResult = onBindResult

        disposable = complete()
                .delay(3000L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe {
                    Log.i(javaClass.simpleName, "bind: Car is not connected-callback is called from bind")
                    onBindResult(car != null)
                }

        car = Car.createCar(context, this)
        car?.connect()
    }

    @SuppressLint("LogNotTimber")
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.i(javaClass.simpleName, "onServiceConnected: Car is connected-callback is called from onServiceConnected")
        onBindResult(car != null)
        if (!isDDregsitered) {
            DriveModeChangeListener().registerListener()
            isDDregsitered = true
        }
        disposable?.dispose()
    }

    @SuppressLint("LogNotTimber")
    override fun onServiceDisconnected(name: ComponentName?) {
        Log.i(javaClass.simpleName, "onServiceDisconnected: Car is disconnected")
        //no-op
    }

    companion object {
        var car: Car? = null
        private var isDDregsitered: Boolean = false
    }
}