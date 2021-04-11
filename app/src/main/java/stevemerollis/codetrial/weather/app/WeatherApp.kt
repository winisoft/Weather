package stevemerollis.codetrial.weather.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import stevemerollis.codetrial.weather.util.lo
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.lang.Thread.setDefaultUncaughtExceptionHandler

@HiltAndroidApp
open class WeatherApp: Application() {

    override fun attachBaseContext(base: Context?) {
        lo.logD { "Launching GM Payment app..."}
        super.attachBaseContext(base)
        System.setProperty("kotlinx.coroutines.debug", "on")
        System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace")
        setDefaultUncaughtExceptionHandler { _, e ->
            lo.logE(e) {
                "Uncaught exception! \n${e.message}"
            }
        }

        Timber.plant(DebugTree())
    }

    object GlobalHandler : Thread.UncaughtExceptionHandler {
        override fun uncaughtException(t: Thread, e: Throwable) {
            lo.logE(e) { "Uncaught exception! Would have crashed the app." }
        }
    }
}