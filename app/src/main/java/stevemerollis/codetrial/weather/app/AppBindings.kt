package stevemerollis.codetrial.weather.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dispatch.core.DefaultCoroutineScope
import dispatch.core.IOCoroutineScope
import dispatch.core.MainCoroutineScope
import dispatch.core.MainImmediateCoroutineScope
import stevemerollis.codetrial.weather.activity.WeatherFragmentFactory
import stevemerollis.codetrial.weather.activity.WeatherNavHostFragment
import stevemerollis.codetrial.weather.currently.frag.CurrentlyFragment
import stevemerollis.codetrial.weather.fragment.FragmentKey
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindings {

    @Binds
    abstract fun bindFragmentFactory(factory: WeatherFragmentFactory): FragmentFactory

    @Binds
    abstract fun bindInjectingNavHost(injector: WeatherNavHostFragment): NavHostFragment

}