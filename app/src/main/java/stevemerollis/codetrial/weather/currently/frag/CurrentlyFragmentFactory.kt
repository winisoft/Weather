package stevemerollis.codetrial.weather.currently.frag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject


@ExperimentalCoroutinesApi
class CurrentlyFragmentFactory
@Inject
constructor(
    private val someString: String
): FragmentFactory(){

    @FlowPreview
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            CurrentlyFragmentFactory::class.java.name -> {
                val fragment = CurrentlyFragment()
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}