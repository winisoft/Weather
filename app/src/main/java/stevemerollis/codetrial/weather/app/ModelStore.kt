package stevemerollis.codetrial.weather.app

import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.intention.Intention

interface ModelStore<S> {
    suspend fun process(intent: Intention<S>)
    fun modelState(): Flow<S>
}