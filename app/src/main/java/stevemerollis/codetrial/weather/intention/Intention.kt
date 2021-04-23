package stevemerollis.codetrial.weather.intention

import stevemerollis.codetrial.weather.app.StateContributor

interface Intention<T> {
    suspend fun execute()
}
