package stevemerollis.codetrial.weather.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Sys(
    // 1
    val type: Int,
    // 5122
    val id: Int,
    // 0.0139
    val message: Double,
    /**
     * two-digit ISO Code
     * ex: US
     */
    val country: String,
    /**
     * in Epoch seconds
     * ex: 1560343627
     */
    val sunrise: Int, // 1560343627
    /**
     * in Epoch seconds
     * ex: 1560396563
     */
    val sunset: Int
) : Parcelable