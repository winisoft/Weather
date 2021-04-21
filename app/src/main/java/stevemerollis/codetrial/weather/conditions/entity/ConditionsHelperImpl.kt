package stevemerollis.codetrial.weather.conditions.entity

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.matteobattilana.weather.PrecipType
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import stevemerollis.codetrial.weather.api.options.SpeedUnits
import stevemerollis.codetrial.weather.api.options.TempUnits
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.currently.app.CurrentlyResponse
import stevemerollis.codetrial.weather.currently.view.*
import java.time.LocalDateTime
import javax.inject.Inject

class ConditionsHelperImpl
@Inject
constructor()
: ConditionsHelper {

    override fun getCurrently(
        uom: UnitsOfMeasure,
        currentlyResponse: CurrentlyResponse
    ): CurrentlyLayoutModel {
        return CurrentlyLayoutModel(
            weatherViewModel = WeatherViewModel(
                fadeOutPercent = 1.0f,
                angle = 180,
                speed = 25,
                emissionRate = 1.0f,
                precipitationType = PrecipType.RAIN,
            ),
            summary = Summary(
                temperature = 70,
                temperatureUnits = TempUnits.Fahrenheit,
                temperatureString = "70 [degrees] F",
                thermometerImage = getThermometerImageForTemp(uom, 70),
                condition = ""
            ),
            wind = Wind(
                reading = 5,
                units = SpeedUnits.MilesPerHour,
                title = "",
                description = "",
                degrees = 90,
                icon = ImageVector.Builder("", 0.dp, 0.dp, 0.0f, 0.0f).build()
            ),
            clouds = Clouds(
                reading = 10_500,
                units = UnitsOfMeasure.Metric,
                visibility = "",
                title = "",
                description = ""
            ),
            background = DataStats(
                location = "",
                recordedAt = LocalDateTime.MIN,
                age = ""
            )
        )
    }

    override fun getThermometerImageForTemp(uom: UnitsOfMeasure, reading: Int): ImageVector = when (uom.tempUnits) {
        is TempUnits.Fahrenheit -> {
            when {
                reading < 0 -> FontAwesomeIcons.Solid.ThermometerEmpty
                reading in 0..32 -> FontAwesomeIcons.Solid.ThermometerQuarter
                reading in 32..64 -> FontAwesomeIcons.Solid.ThermometerHalf
                reading in 64..96 -> FontAwesomeIcons.Solid.ThermometerThreeQuarters
                reading > 96 -> FontAwesomeIcons.Solid.ThermometerFull
                else -> throw Exception()
            }
        }
        is TempUnits.Celsius -> {
            when {
                reading < -18 -> FontAwesomeIcons.Solid.ThermometerEmpty
                reading in -18..-1 -> FontAwesomeIcons.Solid.ThermometerQuarter
                reading in -1..18 -> FontAwesomeIcons.Solid.ThermometerHalf
                reading in 18..35 -> FontAwesomeIcons.Solid.ThermometerThreeQuarters
                reading > 35 -> FontAwesomeIcons.Solid.ThermometerFull
                else -> throw Exception()
            }
        }
        is TempUnits.Kelvin -> {
            when {
                reading < 255 -> FontAwesomeIcons.Solid.ThermometerEmpty
                reading in 255..273 -> FontAwesomeIcons.Solid.ThermometerQuarter
                reading in 273..290 -> FontAwesomeIcons.Solid.ThermometerHalf
                reading in 290..308 -> FontAwesomeIcons.Solid.ThermometerThreeQuarters
                reading > 308 -> FontAwesomeIcons.Solid.ThermometerFull
                else -> throw Exception()
            }
        }
    }
}