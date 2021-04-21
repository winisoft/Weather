package stevemerollis.codetrial.weather.currently.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.app.Repository
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.network.call.NetworkResult
import stevemerollis.codetrial.weather.viewmodel.UseCase


interface CurrentlyRepository: Repository {

    suspend operator fun invoke(
        scope: CoroutineScope,
        appId: String,
        uom: UnitsOfMeasure
    ): Job

    val resultFlow: Flow<Repository.Result<CurrentlyResponse>>
}