package stevemerollis.codetrial.weather.app

import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stevemerollis.codetrial.weather.activity.WeatherFragmentFactory
import stevemerollis.codetrial.weather.activity.WeatherNavHostFragment
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs
import stevemerollis.codetrial.weather.api.id.AppIdImpl
import stevemerollis.codetrial.weather.api.id.AppIdUtil

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindings {

    @Binds
    abstract fun bindFragmentFactory(factory: WeatherFragmentFactory): FragmentFactory

    @Binds
    abstract fun bindInjectingNavHost(injector: WeatherNavHostFragment): NavHostFragment

    @Binds
    abstract fun provideAppIdUtil(implementation: AppIdImpl): AppIdUtil

    @Binds
    abstract fun bindPrefsStore(prefsManager: PreferenceManager): Prefs
}