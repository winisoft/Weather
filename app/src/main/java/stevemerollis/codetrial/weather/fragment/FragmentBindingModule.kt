package stevemerollis.codetrial.weather.fragment

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment

@Module
@InstallIn(SingletonComponent::class)
abstract class FragmentBindingModule {


    @Binds
    @IntoMap
    @ExperimentalCoroutinesApi
    @OptIn(FlowPreview::class)
    @FragmentKey(CurrentlyFragment::class)
    abstract fun bindCurrentlyFragment(fragment: CurrentlyFragment): Fragment


}