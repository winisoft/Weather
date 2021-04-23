package stevemerollis.codetrial.weather.async

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dispatch.core.*

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