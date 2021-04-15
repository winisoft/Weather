package stevemerollis.codetrial.weather.currently

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dispatch.android.viewmodel.DispatchViewModel
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepositoryImpl
import stevemerollis.codetrial.weather.network.helper.NetworkHelper

object CurrentlyModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object App {

        @OptIn(FlowPreview::class)
        @Provides
        fun provideCurrentlyRepository(netHelp: NetworkHelper)
        : CurrentlyRepository =
            CurrentlyRepositoryImpl(netHelp)
    }
}