package stevemerollis.codetrial.weather.currently

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dispatch.core.DefaultCoroutineScope
import dispatch.core.IOCoroutineScope
import kotlinx.coroutines.*
import stevemerollis.codetrial.weather.app.AppCoroScope
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.currently.vm.GetCurrentWeather
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.util.AppIdUtil
import stevemerollis.codetrial.weather.viewmodel.UseCase

@Module
@InstallIn(ViewModelComponent::class)
object CurrentlyModule {

    @Provides
    @ViewModelScoped
    fun provideJob(appScope: AppCoroScope)
    : Job =
        SupervisorJob(parent = appScope.coroutineContext.job)

    @Provides
    @ViewModelScoped
    fun provideCoroutineScope(job: Job)
    : CoroutineScope = DefaultCoroutineScope() + job

    @Provides
    fun provideGetCurrentWeatherUseCase(
        coroutineScope: IOCoroutineScope,
        appIdUtil: AppIdUtil,
        preferenceManager: PreferenceManager,
        currentlyRepository: CurrentlyRepository
    ): UseCase<CurrentlyLayoutModel> =
        GetCurrentWeather(
            coroutineScope,
            appIdUtil,
            preferenceManager,
            currentlyRepository
        )

}