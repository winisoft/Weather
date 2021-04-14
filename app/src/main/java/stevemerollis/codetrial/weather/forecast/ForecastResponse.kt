package stevemerollis.codetrial.weather.forecast

import com.squareup.moshi.Json

/**
 * Data class representing the API response to the 'forecast' controller of the OpenWeatherApi
 */
data class ForecastResponse(
    @Json(name = "list") val list: List<DataItem>
)