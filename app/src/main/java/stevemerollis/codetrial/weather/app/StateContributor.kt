package stevemerollis.codetrial.weather.app

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.intention.Intention

interface StateContributor<S> {

    val intentionChannel: Channel<Intention<S>>

    val _resultFlow: MutableStateFlow<S>

    val resultFlow: StateFlow<S>

    /**
     * Model will receive intents to be processed via this function.
     *
     * ModelState is immutable. Processed intents will work much like `copy()`
     * and create a new (modified) modelState from an old one.
     */
    suspend fun onIntention(intent: Intention<S>)

    /**
     * Collectable stream of changes to ModelState
     *
     * Every time a modelState is replaced by a new one, this observable will
     * fire.
     *
     * This is what views will subscribe to.
     */
    fun fulfill(intent: Intention<S>): Flow<S>

    sealed class Model<S> {
        class Init<S>: Model<S>()
        class Loading<S>: Model<S>()
        data class Result<S>(val value: S): Model<S>()
        object Error: Model<Nothing>()
    }
}