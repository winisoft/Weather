package stevemerollis.codetrial.weather.fragment

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.multibindings.IntoMap
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment

abstract class FragmentBindingModule {

    @Binds @IntoMap @FragmentKey(CurrentlyFragment::class)
    abstract fun bindCurrentlyFragment(frag: CurrentlyFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory)
}