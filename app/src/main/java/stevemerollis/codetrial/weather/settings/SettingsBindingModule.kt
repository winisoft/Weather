package stevemerollis.codetrial.weather.settings

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBindingModule {

//    @Binds
//    abstract fun bindPrefsStore(prefsManager: PreferenceManager): Prefs
}