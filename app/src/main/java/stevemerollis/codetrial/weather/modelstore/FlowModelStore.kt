package stevemerollis.codetrial.weather.modelstore

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import stevemerollis.codetrial.weather.app.Intent
import stevemerollis.codetrial.weather.app.ModelStore
import stevemerollis.codetrial.weather.intention.Intention

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
open class FlowModelStore<S>(initialState: S): ModelStore<S> {

    private val scope = MainScope()

    protected val intentionChannel = Channel<Intention<S>>()

    private val modelChannel = ConflatedBroadcastChannel(initialState)

    override suspend fun process(intent: Intention<S>) {
        intentionChannel.send(intent)
    }

    override fun modelState(): Flow<S> {
        return modelChannel.asFlow()
    }

    fun close() {
        intentionChannel.close()
        modelChannel.close()
        scope.cancel()
    }

    init {
        scope.launch {
            while (isActive) modelChannel.offer(intentionChannel.receive().reduce(modelChannel.value))
        }
    }
}