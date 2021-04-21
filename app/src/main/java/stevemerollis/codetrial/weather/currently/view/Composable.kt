package stevemerollis.codetrial.weather.currently.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.WeatherIcons
import compose.icons.weathericons.*

@Composable
fun CurrentlyStats(model: CurrentlyLayoutModel) {

    Row {
        Statistic(model.summary.thermometerImage, "Temperature", model.summary.temperatureString)
        Statistic(WeatherIcons.StrongWind, "Wind", model.wind.description)
        Statistic(WeatherIcons.Cloud, "Clouds", model.clouds.description)
        Statistic(WeatherIcons.Time1, "Recorded", model.background.recordedAt.toString())
        Statistic(WeatherIcons.Sunrise, "Sunrise", "8:02 a.m.")
        Statistic(WeatherIcons.Sunset, "Sunset", "9:01 p.m.")
    }
}

@Composable
fun Statistic(icon: ImageVector, title: String, primary: String, secondary: String? = null) {
    Box {
        Column {
            Icon(imageVector = icon, contentDescription = "")
            Text(text = title)
            Text(text = primary)
        }
    }
}