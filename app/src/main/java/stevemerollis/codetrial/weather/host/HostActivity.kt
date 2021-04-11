package stevemerollis.codetrial.weather.host

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewBinding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import stevemerollis.codetrial.weather.distraction.DriverDistractionListener
import stevemerollis.codetrial.weather.host.HostViewModel
import dagger.hilt.android.AndroidEntryPoint
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.databinding.ActivityHostBinding
import stevemerollis.codetrial.weather.util.lo.logD


@AndroidEntryPoint
class HostActivity : FragmentActivity() {

    private val viewModel: HostViewModel by viewModels()
    private val viewBinding: ActivityHostBinding? by viewBinding(ActivityHostBinding::inflate)
    private val navController: NavController by lazy { findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD { "$TAG: launched" }
        viewBinding?.apply {
            navController.setGraph(R.navigation.nav_graph)
            setContentView(root)
            setActionBar(toolbar)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        handlePermissionsResult(requestCode, permissions, grantResults,
            onPermissionGranted = {
                if (permissions.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
                }
            },
            onPermissionDenied = {

            },
            onPermissionDeniedPermanently = {

            }
        )
    }

    fun isGranted(permission: AppPermission) = run {
        this.let {
            (PermissionChecker.checkSelfPermission(it, permission.permissionName
            ) == PermissionChecker.PERMISSION_GRANTED)
        } ?: false
    }

    fun Activity.shouldShowRationale(permission: AppPermission) = run {
        shouldShowRequestPermissionRationale(permission.permissionName)
    }

    fun requestPermission(permission: AppPermission) {
        requestPermissions(arrayOf(permission.permissionName), permission.requestCode)
    }

    fun handlePermission(
            permission: AppPermission,
            onGranted: (AppPermission) -> Unit,
            onDenied: (AppPermission) -> Unit,
            onRationaleNeeded: ((AppPermission) -> Unit)? = null
    ) {
        when {
            isGranted(permission) ->onGranted.invoke(permission)
            shouldShowRationale(permission) ->  onRationaleNeeded?.invoke(permission)
            else ->  onDenied.invoke(permission)
        }
    }

    fun handlePermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray,
            onPermissionGranted: (AppPermission) -> Unit,
            onPermissionDenied: ((AppPermission) -> Unit)? = null,
            onPermissionDeniedPermanently: ((AppPermission) -> Unit)? = null
    ) {

        AppPermission.permissions.find {
            it.requestCode == requestCode
        }?.let { appPermission ->
            val permissionGrantResult = mapPermissionsAndResults(permissions, grantResults
            )[appPermission.permissionName]
            when {
                PermissionChecker.PERMISSION_GRANTED == permissionGrantResult -> {
                    onPermissionGranted(appPermission)
                }
                shouldShowRationale(appPermission) -> onPermissionDenied?.invoke(appPermission)
                else -> {
                    onPermissionDeniedPermanently?.invoke(appPermission)
                }
            }
        }
    }


    private fun mapPermissionsAndResults(
            permissions: Array<out String>, grantResults: IntArray
    ): Map<String, Int> = permissions.mapIndexedTo(mutableListOf<Pair<String, Int>>()
    ) { index, permission -> permission to grantResults[index] }.toMap()


    sealed class AppPermission(
        val permissionName: String,
        val requestCode: Int,
        val deniedMessageId: Int,
        val explanationMessageId: Int
    ) {
        companion object {
            val permissions: List<AppPermission> by lazy { listOf(PermissionLocation) }
        }

        object PermissionLocation: AppPermission(Manifest.permission.CALL_PHONE, 100,
                R.string.mybrand_10_5_1_purchaseFail_csm, R.string.mybrand_10_5_1_purchaseFail_csm
        )
    }

    companion object {
        val TAG: String = HostActivity::class.simpleName.toString()
    }
}





