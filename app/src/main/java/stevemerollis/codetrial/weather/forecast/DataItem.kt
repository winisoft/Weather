package stevemerollis.codetrial.weather.forecast

import com.squareup.moshi.Json
import stevemerollis.codetrial.weather.conditions.entity.Main
import stevemerollis.codetrial.weather.conditions.entity.Weather


/**
 * Data class representing one of several forecasted weather reports
 */
data class DataItem(
    @Json(name = "main") val main: Main,
    @Json(name = "weather") val weather: List<Weather>,
    @Json(name = "dt_txt") val dateText: String
)