package stevemerollis.codetrial.weather.activity

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class WeatherNavHostFragment
@Inject
constructor(
    private val injectingFragmentFactory: WeatherFragmentFactory
) : NavHostFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = injectingFragmentFactory
    }
}