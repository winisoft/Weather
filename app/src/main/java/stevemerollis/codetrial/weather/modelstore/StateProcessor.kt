package stevemerollis.codetrial.weather.modelstore

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.app.StateContributor
import stevemerollis.codetrial.weather.intention.Intention

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
abstract class StateProcessor<S>(

) : StateContributor<S> {

    override val intentionChannel = Channel<Intention<S>>()

//    final override val _resultFlow: MutableStateFlow<S>?

//    override val resultFlow: StateFlow<S> = _resultFlow

    /**
     * Model will receive intentions to be processed via this function.
     *
     * ModelState is immutable. Processed intents will work much like `copy()`
     * and create a new (modified) modelState from an old one.
     */
    override suspend fun onIntention(intent: Intention<S>) = intentionChannel.send(intent)

    /**
     * Flowing stream of changes to ModelState
     *
     * Every time a modelState is replaced by a new one, this observable will
     * fire.
     *
     * This is what views will subscribe to.
     */
    override fun fulfill(intent: Intention<S>): Flow<S> = resultFlow as Flow<S>

    fun close() {
        intentionChannel.close()
        //scope.cancel()
    }

    private fun launchCollect() {
//        scope.launch {
//            while (isActive) intentionChannel.consumeAsFlow().onEach { _resultFlow.value = it.execute() }
//        }
    }

    init {
        launchCollect()
    }
}