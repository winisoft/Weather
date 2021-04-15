package stevemerollis.codetrial.weather.settings.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure

interface WeatherPreferences {

    fun getUnitOfMeasure(): UnitsOfMeasure

    suspend fun setUnitOfMeasure(uom: UnitsOfMeasure)

    fun isNightMode(): Flow<Boolean>

    suspend fun toggleNightMode()

    companion object {
        const val PREFS_NAME = ""
        const val STORE_NAME = "learning_data_store"
    }
}