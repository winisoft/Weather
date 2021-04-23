package stevemerollis.codetrial.weather.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.location.ILocationHelper
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory
@Inject
constructor(
    val locationHelper: ILocationHelper
): FragmentFactory(){

    @FlowPreview
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            MainFragment::class.java.name -> MainFragment()
            CurrentlyFragment::class.java.name -> CurrentlyFragment(locationHelper)
            else -> super.instantiate(classLoader, className)
        }
    }
}