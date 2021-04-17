package stevemerollis.codetrial.weather.app

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dispatch.core.*
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {

    @Provides
    fun provideIoAppScope() : IOCoroutineScope = IOCoroutineScope()

    @Provides
    fun provideMainAppScope(): MainCoroutineScope = MainCoroutineScope()

    @Provides
    fun provideMainAppScopeNow(): MainImmediateCoroutineScope = MainImmediateCoroutineScope()

    @Provides
    fun provideDefaultAppScope(): DefaultCoroutineScope = DefaultCoroutineScope()

    @Provides
    fun provideUnconfinedAppScope(): UnconfinedCoroutineScope = UnconfinedCoroutineScope()
}