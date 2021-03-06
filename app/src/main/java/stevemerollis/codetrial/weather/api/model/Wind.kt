package stevemerollis.codetrial.weather.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Wind(
    val speed: Double,
    @Json(name = "deg") val degrees: Int
) : Parcelable