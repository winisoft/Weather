package stevemerollis.codetrial.weather.currently.vm

import android.graphics.drawable.Drawable

data class CurrentlyViewProperties(
    val condition: String,
    val icon: Drawable,
    val wind: String,
    val clouds: String,
    val visibility: String,
    val location: String,
    val reportedAt: String
)