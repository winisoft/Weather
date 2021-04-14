package stevemerollis.codetrial.weather.activity

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FragmentInjectorEntryPoint {
    fun getFragmentInjector(): WeatherFragmentFactory
}