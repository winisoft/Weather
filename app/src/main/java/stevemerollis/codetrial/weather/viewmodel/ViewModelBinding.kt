package stevemerollis.codetrial.weather.viewmodel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel


@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBinding {

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun bindCurrentlyViewModel(vm: CurrentlyViewModel): WeatherViewModel
}