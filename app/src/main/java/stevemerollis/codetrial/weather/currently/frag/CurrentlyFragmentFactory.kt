package stevemerollis.codetrial.weather.currently.frag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.location.ILocationHelper
import javax.inject.Inject


@ExperimentalCoroutinesApi
class CurrentlyFragmentFactory
@Inject
constructor(
    private val locationHelper: ILocationHelper
): FragmentFactory(){

    @FlowPreview
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            CurrentlyFragment::class.java.name -> CurrentlyFragment(locationHelper)
            else -> super.instantiate(classLoader, className)
        }
    }
}