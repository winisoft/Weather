package stevemerollis.codetrial.weather.conditions

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Main(
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    /**
     * Minimum temperature at the moment. This is deviation from current temp that is possible
     * for large cities.
     */
    @Json(name = "temp_min") val tempMin: Double,
    /**
     * Maximum temperature at the moment. This is deviation from current temp that is possible for large cities
     */
    @Json(name = "temp_max") val tempMax: Double,
    /**
     * The atmospheric pressure (at sea level, if the [seaLevel] or [groundLevel] readings are null,
     * expressed in hectopascals
     */
    val pressure: Double, // 1023 //TODO: Int for currently, double for history..?
    /**
     * The air humidity expressed as a percent integer (0 - 100)
     */
    val humidity: Int,
    /**
     * Atmospheric pressure at the same altitude as the sea level, in hectopascals
     */
    @Json(name = "sea_level") val seaLevel: Double?, // 1039.34
    /**
     * Atmospheric pressure at the same altitude as the ground, in hectopascals
     */
    @Json(name = "grnd_level") val groundLevel: Double?
) : Parcelable