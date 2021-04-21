package stevemerollis.codetrial.weather.currently

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.app.Intent
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.intention.Intention
import stevemerollis.codetrial.weather.modelstore.FlowModelStore


@FlowPreview
object CurrentlyModelStore
    : FlowModelStore<CurrentlyLayoutModel>(CurrentlyLayoutModel.INITIAL) {

    override suspend fun process(intent: Intention<CurrentlyLayoutModel>) {
        super.process(intent)
    }

    override fun modelState(): Flow<CurrentlyLayoutModel> {
        return super.modelState()
    }
}