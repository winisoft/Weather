package stevemerollis.codetrial.weather.api.id

/**
 * Intended to be a point of abstraction for concealing the "app id" used.
 */
interface AppIdUtil {

    fun getApiToken(): String
}