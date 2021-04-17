package stevemerollis.codetrial.weather.activity

import androidx.datastore.preferences.core.Preferences
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.settings.app.Prefs


@EntryPoint
@InstallIn(ActivityComponent::class)
interface BaseActivityEntryPoint {
    fun getFragmentInjector(): WeatherFragmentFactory
    fun getPrefs(): Prefs
}