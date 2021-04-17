package stevemerollis.codetrial.weather.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Clouds(
    /**
     * Current cloud cover, expressed as a percent integer (0-100)
     */
    val all: Int
) : Parcelable