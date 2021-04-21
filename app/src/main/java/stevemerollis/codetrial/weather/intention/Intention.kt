package stevemerollis.codetrial.weather.intention

interface Intention<T> {
    fun reduce(previous: T): T
}

/**
 * Represents some work delegated outside of the scope of updating the ModelStore in play.
 * This preserves the unidirectional data flow and immutability by isolating these workers into a DSL
 */
fun <T> sideEffect(block: T.() -> Unit) : Intention<T> = object : Intention<T> {
    override fun reduce(previous: T): T = previous.apply(block)
}