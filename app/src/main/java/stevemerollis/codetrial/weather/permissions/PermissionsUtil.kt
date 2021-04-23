package stevemerollis.codetrial.weather.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Handy util to abstract away a lot of the activity-side headaches of requesting permissions
 */
class PermissionsUtil @Inject constructor() {

    private var disposable: Disposable? = null

    /**
     * Performs the work of prompting the user to accept or deny a given permission, even with regard
     * to permissions that have been denied with the condition "do not ask again".
     */
    fun requestPermission(activity: Activity, @PermissionTypes permission: String,
                          @RequestCode requestCode: Int, onPermissionResult: (granted: Boolean) -> Unit) {

        setupPermissionListener(onPermissionResult)

        if (checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(activity, arrayOf(permission), requestCode)
        } else {
            onPermissionResult(true)
        }
    }

    /**
     * Registers to be notified of the Observable result of a permission request and performs the
     * onPermissionResult function consequently to an onNext() call. No error is possible so there
     * is no function to handle this case. When the onComplete() method is called, the listener may be
     * disposed of.
     */
    private inline fun setupPermissionListener(crossinline onPermissionResult: (granted: Boolean) -> Unit) {
        disposable = PermissionSignal.getEventObservable()
            .subscribe({
                    data -> onPermissionResult(data.result.isNotEmpty() &&
                    data.result[0] == PackageManager.PERMISSION_GRANTED)
            },
            {},
            { disposable?.dispose() })
    }

    companion object {
        //constant values representing the request codes of the permissions WeatherOrNot must request
        const val CODE_ACCESS_LOCATION = 100
    }
}