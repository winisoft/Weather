package stevemerollis.codetrial.weather.currently.view

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matteobattilana.weather.PrecipType
import com.github.matteobattilana.weather.WeatherView
import compose.icons.FontAwesomeIcons
import compose.icons.WeatherIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import compose.icons.weathericons.*
import stevemerollis.codetrial.weather.api.options.SpeedUnits
import stevemerollis.codetrial.weather.api.options.TempUnits
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.viewmodel.State
import java.time.LocalDateTime

data class WeatherViewModel(
    val fadeOutPercent: Float,
    val angle: Int,
    val speed: Int,
    val emissionRate: Float,
    val precipitationType: PrecipType,
)

data class Summary(
    val temperature: Int,
    val temperatureUnits: TempUnits,
    val temperatureString: String,
    val thermometerImage: ImageVector,
    val condition: String
)

data class Wind(
    val reading: Int,
    val units: SpeedUnits,
    val title: String,
    val description: String,
    val degrees: Int,
    val icon: ImageVector
)

data class Clouds(
    val reading: Int,
    val units: UnitsOfMeasure,
    val title: String,
    val description: String,
    val visibility: String
)

data class DataStats(
    val location: String,
    val recordedAt: LocalDateTime,
    val age: String
)

data class CurrentlyLayoutModel(
    val weatherViewModel: WeatherViewModel,
    val summary: Summary,
    val wind: Wind,
    val clouds: Clouds,
    val background: DataStats
) {

    companion object {
        val INITIAL = CurrentlyLayoutModel(
            weatherViewModel =
                WeatherViewModel(0.0f, 0, 0, 0.0f, PrecipType.CUSTOM),
            summary =
                Summary(0, TempUnits.Kelvin, "",
                    ImageVector.Builder("", 0.dp, 0.dp, 0.0f, 0.0f
                ).build(), ""),
            wind = Wind(0, SpeedUnits.MilesPerHour, "", "", 0,
                ImageVector.Builder("", 0.dp, 0.dp, 0.0f, 0.0f).build()),
            clouds = Clouds(0, UnitsOfMeasure.Metric, "", "", ""),
            background = DataStats("", LocalDateTime.MIN, "")
        )
    }
}