package stevemerollis.codetrial.weather.currently.app

import androidx.annotation.Keep
import com.squareup.moshi.Json
import stevemerollis.codetrial.weather.api.model.*
import stevemerollis.codetrial.weather.location.Coordinates


@Keep
data class CurrentlyResponse(
    val coordinates: Coordinates,
    val weather: List<Condition>,
    /**
     * Internal parameter to distinguish source of data
     */
    val base: String,
    /**
     * Main element of the reading, containing the most precise figures
     */
    val main: Main,
    /**
     * In configured distance units
     * ex. 16093
     */
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    /**
     * Data received dateTime
     * ex: 1560350645 (unix, UTC)
     */
    @Json(name = "dt") val dt: Int,
    val sys: Sys,
    /**
     * Shift in seconds from UTC
     * ex: -25200
     */
    val timezone: Int,
    /**
     * City ID, ex: 420006353
     */
    val id: Int,
    /**
     * City name
     */
    val name: String,
    /**
     * Internal parameter, presumably http response code
     */
    val cod: Int
)