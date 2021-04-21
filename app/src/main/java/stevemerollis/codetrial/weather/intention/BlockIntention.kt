package stevemerollis.codetrial.weather.intention

import stevemerollis.codetrial.weather.app.Intent

class BlockIntention<T>(val block: T.() -> T) : Intention<T> {
    override fun reduce(previous: T): T = block(previous)
}

/**
 * DSL function to help build intents from code blocks.
 */
fun <T> intention(block: T.() -> T) = BlockIntention(block)