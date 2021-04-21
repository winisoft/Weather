@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.currently.vm

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.matteobattilana.weather.PrecipType
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Hourglass
import compose.icons.fontawesomeicons.solid.Thermometer
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
//import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.api.id.AppIdUtil
import stevemerollis.codetrial.weather.api.options.SpeedUnits
import stevemerollis.codetrial.weather.api.options.TempUnits
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.app.Repository
import stevemerollis.codetrial.weather.conditions.entity.ConditionsHelper
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.currently.view.*
import stevemerollis.codetrial.weather.viewmodel.UseCase
import stevemerollis.codetrial.weather.viewmodel.WeatherUseCase
import java.time.LocalDateTime
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentWeather
@Inject
constructor(
    private val conditionsHelper: ConditionsHelper,
    private val appIdUtil: AppIdUtil,
    private val prefManager: PreferenceManager,
    private val currentlyRepository: CurrentlyRepository
): WeatherUseCase<CurrentlyLayoutModel>() {

    override suspend operator fun invoke(scope: CoroutineScope): Job = scope.launch(scope.newCoroutineContext(scope.coroutineContext)) {
        val uom = prefManager.getUnitOfMeasure().single()
        resultFlow.transform {
            if (it is Repository.Result.Success<*>) {
                emit(transform(it.data as Repository.Result<CurrentlyResponse>, uom))
            }
        }
        currentlyRepository(
            scope,
            appIdUtil.getApiToken(),
            uom
        ).join()
    }

    override val resultFlow: Flow<UseCaseResult<CurrentlyLayoutModel>> = emptyFlow()

    private fun transform(
        repoResult: Repository.Result<CurrentlyResponse>,
        uom: UnitsOfMeasure
    ): UseCase.Result<CurrentlyLayoutModel> {
        repoResult as Repository.Result.Success<CurrentlyResponse>
        val layoutProperties = conditionsHelper.getCurrently(uom, repoResult.data)

        return UseCase.Result.Success(layoutProperties)
    }

    sealed class UseCaseResult<T>: UseCase.Result<T> {
        data class Success<T>(val model: CurrentlyLayoutModel): UseCaseResult<T>()
        object Error: UseCaseResult<Nothing>()
    }

    private fun CurrentlyResponse.map(): UseCaseResult.Success<CurrentlyLayoutModel> {
        return UseCaseResult.Success(
            CurrentlyLayoutModel(
                weatherViewModel = WeatherViewModel(
                    fadeOutPercent = 1.0f,
                    angle = 180,
                    speed = 25,
                    emissionRate = 1.0f,
                    precipitationType = PrecipType.RAIN,
                ),
                summary = Summary(
                    temperature = 70,
                    temperatureUnits = TempUnits.Fahrenheit,
                    temperatureString = "70 [degrees] F",
                    thermometerImage = ImageVector.Builder("", 0.dp, 0.dp, 0.0f, 0.0f).build(),
                    condition = ""
                ),
                wind = Wind(
                    reading = 5,
                    units = SpeedUnits.MilesPerHour,
                    title = "",
                    description = "",
                    degrees = 90,
                    icon = ImageVector.Builder("", 0.dp, 0.dp, 0.0f, 0.0f).build()
                ),
                clouds = Clouds(
                    reading = 10_500,
                    units = UnitsOfMeasure.Metric,
                    visibility = "",
                    title = "",
                    description = ""
                ),
                background = DataStats(
                    location = "",
                    recordedAt = LocalDateTime.MIN,
                    age = ""
                )
            )
        )
    }
}