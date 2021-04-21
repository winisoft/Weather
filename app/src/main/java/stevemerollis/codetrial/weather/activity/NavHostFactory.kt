package stevemerollis.codetrial.weather.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

//@ExperimentalCoroutinesApi
//class NavHostFactory
//@Inject
//constructor(
//    private val fragmentFactory: WeatherFragmentFactory
//): FragmentFactory(){
//
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
//@ExperimentalCoroutinesApi
//class WeatherNavHostFactory
//@Inject
//constructor(
//    private val fragmentFactory: WeatherFragmentFactory
//): FragmentFactory(){
//
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