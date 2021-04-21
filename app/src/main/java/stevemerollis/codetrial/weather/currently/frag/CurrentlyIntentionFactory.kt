package stevemerollis.codetrial.weather.currently.frag

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.CurrentlyModelStore
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.intention.IntentionFactory
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel.Intentions.LoadUI.reduce
import stevemerollis.codetrial.weather.intention.Intention
import stevemerollis.codetrial.weather.intention.intention

@ExperimentalCoroutinesApi
@FlowPreview
object CurrentlyIntentionFactory: IntentionFactory<CurrentlyViewEvent> {

    /**
     * Invoked whenever a new view event is emitted.
     */
    override suspend fun process(viewEvent: CurrentlyViewEvent){
        CurrentlyModelStore.process(toIntention(viewEvent))
    }

    private fun toIntention(viewEvent: CurrentlyViewEvent): Intention<CurrentlyLayoutModel> = when (viewEvent) {
        CurrentlyViewEvent.Initial -> InitializeUI()
        CurrentlyViewEvent.Refresh -> ReloadUI()
    }

    class InitializeUI: Intention<CurrentlyLayoutModel> {
        override fun reduce(previous: CurrentlyLayoutModel): CurrentlyLayoutModel =
            previous.copy()
    }

    class ReloadUI: Intention<CurrentlyLayoutModel> {
        override fun reduce(previous: CurrentlyLayoutModel): CurrentlyLayoutModel =
            previous.copy()
    }
}