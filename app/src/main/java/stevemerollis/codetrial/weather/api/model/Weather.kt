package stevemerollis.codetrial.weather.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Parcelize
@Keep
data class Weather(
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val weather: List<Condition>,
    val dt: Int // 1485722804
) : Parcelable