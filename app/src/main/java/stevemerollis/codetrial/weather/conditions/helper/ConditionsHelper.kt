package stevemerollis.codetrial.weather.conditions.helper

import androidx.compose.ui.graphics.vector.ImageVector
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.api.model.CurrentlyResponse
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel

interface ConditionsHelper {

    fun getCurrently(uom: UnitsOfMeasure, currentlyResponse: CurrentlyResponse): CurrentlyLayoutModel

    fun getThermometerImageForTemp(uom: UnitsOfMeasure, reading: Int): ImageVector
}