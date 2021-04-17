package stevemerollis.codetrial.weather.currently.view

import android.graphics.drawable.Drawable

data class CurrentlyLayoutModel(
    val condition: String,
    val icon: Drawable,
    val wind: String,
    val clouds: String,
    val visibility: String,
    val location: String,
    val reportedAt: String
)