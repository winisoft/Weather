package stevemerollis.codetrial.weather.settings

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stevemerollis.codetrial.weather.settings.app.PrefsStore
import stevemerollis.codetrial.weather.settings.app.PrefsStoreImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindPrefsStore(prefsStoreImpl: PrefsStoreImpl): PrefsStore
}