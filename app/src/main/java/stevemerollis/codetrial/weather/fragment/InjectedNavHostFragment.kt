package stevemerollis.codetrial.weather.fragment

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class InjectedNavHostFragment
@Inject
constructor(
    private val injectingFragmentFactory: InjectingFragmentFactory
) : NavHostFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = injectingFragmentFactory
    }
}