package stevemerollis.codetrial.weather.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import stevemerollis.codetrial.weather.fragment.InjectedNavHostFragment

@Module
@InstallIn(FragmentComponent::class)
abstract class NavHostModule {

    @Binds
    abstract fun navHostFragmentInjector()
    : InjectedNavHostFragment
}