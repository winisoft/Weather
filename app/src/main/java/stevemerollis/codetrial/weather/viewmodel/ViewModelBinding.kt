package stevemerollis.codetrial.weather.viewmodel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel


@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBinding {

    @Binds
    @OptIn(FlowPreview::class)
    @ExperimentalCoroutinesApi
    abstract fun bindCurrentlyViewModel(vm: CurrentlyViewModel): WeatherViewModel
}