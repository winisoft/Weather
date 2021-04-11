package stevemerollis.codetrial.weather.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import stevemerollis.codetrial.weather.util.lo
import stevemerollis.codetrial.weather.util.lo.logD
import javax.inject.Inject
import javax.inject.Provider

class InjectingFragmentFactory
@Inject
constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = try {
        loadFragmentClass(classLoader, className).let { fragmentClass ->
            creators[fragmentClass]
        }?.get()
            ?: super.instantiate(classLoader, className)
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

    private fun createFragmentDefault(classLoader: ClassLoader, className: String): Fragment {
        logD { "No creator exists for $className; using default constructor" }
        return super.instantiate(classLoader, className)
    }

    companion object {
        val TAG: String = InjectingFragmentFactory::class.simpleName.toString()
    }
}