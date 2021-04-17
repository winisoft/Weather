package stevemerollis.codetrial.weather.activity

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow


@EntryPoint
@InstallIn(ActivityComponent::class)
interface BaseActivityEntryPoint {
    fun getFragmentInjector(): WeatherFragmentFactory
    fun getThemeFlow(): Flow<Boolean>
}