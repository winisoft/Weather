package stevemerollis.codetrial.weather.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.util.lo.logD
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


//class WeatherFragmentFactory
//@Inject
//constructor(
//    private val fragmentFactory: WeatherFragmentFactory
//): FragmentFactory(){
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        return when (className) {
//
//            WeatherNavHostFragment::class.java.name -> {
//                val fragment = WeatherNavHostFragment(fragmentFactory)
//                fragment
//            }
//
//            else -> super.instantiate(classLoader, className)
//        }
//    }
//}
//
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        val fragmentClass = loadFragmentClass(classLoader, className)
//
//        return providerMap[fragmentClass]
//            ?: providerMap.entries.firstOrNull { fragmentClass.isAssignableFrom(it.key) }?.value
//            ?: super.instantiate(classLoader, className)
//    }
//}