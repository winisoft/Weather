@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
//import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.api.id.AppIdUtil
import stevemerollis.codetrial.weather.conditions.helper.ConditionsHelper
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.view.*
import stevemerollis.codetrial.weather.error.ui.ErrorView
import stevemerollis.codetrial.weather.network.call.NetworkResult
import stevemerollis.codetrial.weather.viewmodel.UseCase
import javax.inject.Inject

@FlowPreview
@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentWeather
@Inject
constructor(
    private val conditionsHelper: ConditionsHelper,
    private val appIdUtil: AppIdUtil,
    private val prefManager: PreferenceManager,
    private val currentlyRepository: CurrentlyRepository
): UseCase<CurrentlyLayoutModel> {

   suspend operator fun invoke(): State = currentlyRepository
       .getCurrentWeather(
           appIdUtil.getApiToken(),
           prefManager.getUnitOfMeasure().single()
       ).let {
           when (it) {
               is NetworkResult.Success<*> -> {
                   val currentlyLayoutModel = CurrentlyLayoutModel.INITIAL
                   State.Result(currentlyLayoutModel)
               }
               else -> {
                   State.Error(object : ErrorView {})
               }
           }
       }

    sealed class State {
        data class Result(val value: CurrentlyLayoutModel): State()
        data class Error(val value: ErrorView): State()
    }
}