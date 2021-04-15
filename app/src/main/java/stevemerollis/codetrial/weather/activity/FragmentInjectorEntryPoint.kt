package stevemerollis.codetrial.weather.activity

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
interface FragmentInjectorEntryPoint {
    fun getFragmentInjector(): WeatherFragmentFactory
}