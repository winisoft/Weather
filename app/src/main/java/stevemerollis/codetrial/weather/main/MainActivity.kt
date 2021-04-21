package stevemerollis.codetrial.weather.main

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.activity.MainActivityEntryPoint
import stevemerollis.codetrial.weather.util.lo.logD

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainNavHostFragment: FragmentContainerView by lazy { findViewById(R.id.nav_host_fragment) }
    private val bottomNavigation: BottomNavigationView by lazy { findViewById(R.id.bottom_navigation) }
    private val navController: NavController by lazy { mainNavHostFragment.findNavController() }
    private val appBarConfiguration by lazy { AppBarConfiguration(
        topLevelDestinationIds = setOf(R.id.fragmentCurrently, R.id.fragmentCurrently)
    )}

    override fun onCreate(savedInstanceState: Bundle?) {
        EntryPointAccessors.fromActivity(this, MainActivityEntryPoint::class.java).apply()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController.setGraph(R.navigation.nav_main)
        setupBottomNavigationBar()
        logD { "$TAG: launched" }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun setupActionBar() {
        setupActionBarWithNavController(navController, AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.fragmentCurrently, R.id.fragmentCurrently)
        ))
    }

    private fun setupBottomNavigationBar() {
        bottomNavigation.setupWithNavController(navController)
    }

    private val entryPoint: MainActivityEntryPoint
        get() = EntryPointAccessors.fromActivity(this, MainActivityEntryPoint::class.java)

    private val MainActivityEntryPoint.themeId: Flow<Int>
        get() = getPrefs().isNightMode().transform { emit(if (it) R.style.AppTheme_Dark else R.style.AppTheme_Light) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        EntryPointAccessors.fromActivity(this, MainActivityEntryPoint::class.java).apply()
        super.onCreate(savedInstanceState, persistentState)
    }

    fun MainActivityEntryPoint.apply() {
        supportFragmentManager.fragmentFactory = getFragmentInjector()
        lifecycleScope.launch {
            setTheme(themeId.single())
            themeId.onEach { setTheme(it); recreate() }.collect()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        handlePermissionsResult(requestCode, permissions, grantResults,
            onPermissionGranted = {
                //if (permissions.contains(Manifest.permission.ACCESS_FINE_LOCATION)) { } else { }
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

        object PermissionLocation: AppPermission(Manifest.permission.ACCESS_FINE_LOCATION, 100,
                R.string.locationDeniedMessage, R.string.locationDeniedExplanation
        )
    }

    companion object {
        val TAG: String = MainActivity::class.simpleName.toString()
    }
}





