package stevemerollis.codetrial.weather.activity

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.main.MainFragmentFactory
import stevemerollis.codetrial.weather.settings.app.Prefs


@EntryPoint
@InstallIn(ActivityComponent::class)
interface MainActivityEntryPoint {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFragmentInjector(): MainFragmentFactory
    fun getPrefs(): Prefs
}