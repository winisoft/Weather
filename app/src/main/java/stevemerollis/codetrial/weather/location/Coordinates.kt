package stevemerollis.codetrial.weather.location

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class Coordinates(
    val lon: Double,
    val lat: Double
): Parcelable
