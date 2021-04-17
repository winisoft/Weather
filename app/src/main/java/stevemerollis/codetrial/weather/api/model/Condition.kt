package stevemerollis.codetrial.weather.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Condition(
    /**
     * Weather condition id
     * ex: 800
     */
    val id: Int,
    /**
     * Group of weather parameters (Rain, Snow, Extreme etc.)
     */
    val main: String,
    /**
     * Weather condition within the group
     * ex: clear sky
     */
    val description: String,
    /**
     *  Weather icon id
     * ex: 01d
     */
    val icon: String
): Parcelable