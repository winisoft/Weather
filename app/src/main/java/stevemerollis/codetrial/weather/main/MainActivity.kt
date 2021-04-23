package stevemerollis.codetrial.weather.main

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.activity.MainActivityEntryPoint
import stevemerollis.codetrial.weather.util.lo.logD

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val entryPoint: MainActivityEntryPoint
        get() = EntryPointAccessors.fromActivity(this, MainActivityEntryPoint::class.java)

    private val MainActivityEntryPoint.themeId: Flow<Int>
        get() = getPrefs().isNightMode().transform { emit(if (it) R.style.AppTheme_Dark else R.style.AppTheme_Light) }

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

    private fun MainActivityEntryPoint.apply() {
        supportFragmentManager.fragmentFactory = getFragmentInjector()
        lifecycleScope.launch {
            setTheme(themeId.single())
            themeId.onEach { setTheme(it); recreate() }.collect()
        }
    }

    companion object {
        val TAG: String = MainActivity::class.simpleName.toString()
    }
}





