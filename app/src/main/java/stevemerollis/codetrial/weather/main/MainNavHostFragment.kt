package stevemerollis.codetrial.weather.main

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.activity.MainActivityEntryPoint
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment
@Inject
constructor() : NavHostFragment(), ReadOnlyProperty<Activity, NavController> {

    lateinit var nav: NavController

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        nav = navController
    }

    @FlowPreview
    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = EntryPointAccessors
            .fromActivity(activity as MainActivity, MainActivityEntryPoint::class.java)
            .getFragmentInjector()
    }

    override fun getValue(thisRef: Activity, property: KProperty<*>): NavController {
        return navController
    }

}