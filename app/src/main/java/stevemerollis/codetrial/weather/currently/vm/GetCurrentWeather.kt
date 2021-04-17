package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
//import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.api.id.AppIdUtil
import stevemerollis.codetrial.weather.viewmodel.UseCase
import stevemerollis.codetrial.weather.viewmodel.WeatherUseCase
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentWeather
@Inject
constructor(
    override val coroutineScope: CoroutineScope,
    private val appIdUtil: AppIdUtil,
    private val prefManager: PreferenceManager,
    private val currentlyRepository: CurrentlyRepository
): WeatherUseCase<CurrentlyLayoutModel>(coroutineScope) {


    override suspend fun getResult(intention: UseCase.Intention): UseCase.Result<CurrentlyLayoutModel> = when(intention) {
        is UseCaseIntention.FetchCurrentWeather -> currentWeatherFetch()
        else -> currentWeatherFetch()
    }

    private suspend fun currentWeatherFetch(): UseCase.Result<CurrentlyLayoutModel> = when (
        val asyncResult = currentlyRepository.getCurrentWeather(
            appId = appIdUtil.getApiToken(),
            uom = prefManager.getUnitOfMeasure().single()
        )
    ) {
        is AsyncResult.Success<*>
            -> UseCaseResult.Success(asyncResult.data as CurrentlyLayoutModel)
        else
            -> UseCaseResult.Error // parse async error as appropriate
    }

    sealed class UseCaseIntention: UseCase.Intention {
        object FetchCurrentWeather: UseCaseIntention()
    }

    sealed class UseCaseResult: UseCase.Result<CurrentlyLayoutModel> {
        data class Success<T>(val data: T): UseCaseResult()
        object Error: UseCaseResult()
    }
}