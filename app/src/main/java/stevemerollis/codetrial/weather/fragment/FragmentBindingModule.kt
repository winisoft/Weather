package stevemerollis.codetrial.weather.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.app.AppCoroScope
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.network.state.NetStateUtil
import stevemerollis.codetrial.weather.network.state.SimpleNetStateUtil
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FragmentBindingModule {

//    @Provides
//    @IntoMap
//    @ClassKey(WeatherNavHostFragment::class)
//    fun provideWeatherNavHostFragment(
//        injectingFragmentFactory: WeatherFragmentFactory
//    ): NavHostFragment = WeatherNavHostFragment(injectingFragmentFactory)
//
//    @Provides
//    fun provideWeatherFragmentFactory(
//        availableClients: Map<Class<out Fragment>, @JvmSuppressWildcards Fragment>
//    ): FragmentFactory = WeatherFragmentFactory(availableClients)

//    @OptIn(FlowPreview::class)
//    @Provides
//    fun fragmentFactoryProviderMap()
//    : Map<Class<out Fragment>, @JvmSuppressWildcards Fragment> = mapOf(
//        CurrentlyFragment::class.java to CurrentlyFragment()
//    )

    @Provides
    @IntoMap
    @OptIn(FlowPreview::class)
    @ExperimentalCoroutinesApi
    @ClassKey(CurrentlyFragment::class)
    fun provideCurrentlyFragment(fragment: CurrentlyFragment): CurrentlyFragment = fragment

    ///region Currently Fragment

//    @Provides @OptIn(FlowPreview::class) @ExperimentalCoroutinesApi
//    fun provideFragmentProviders(
//        availableClients: Map<KClass<out Fragment>, @JvmSuppressWildcards CurrentlyFragment>
//    ): CurrentlyFragment {
//        return availableClients[CurrentlyFragment::class] as? CurrentlyFragment
//            ?: throw Exception("None were provided as entries")
//    }
//    @Provides
//    fun provideFragmentProviderWeatherNavHostFragment(
//        availableClients: Map<KClass<out Fragment>, @JvmSuppressWildcards WeatherNavHostFragment>
//    ): WeatherNavHostFragment {
//        return availableClients[WeatherNavHostFragment::class] ?: throw Exception("None were provided as entries")
//    }
    ///endregion
}