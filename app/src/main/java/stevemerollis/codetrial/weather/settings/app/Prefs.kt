package stevemerollis.codetrial.weather.settings.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import java.io.IOException

interface Prefs: DataStore<Preferences> {

    fun getUnitOfMeasure(): Flow<UnitsOfMeasure>

    suspend fun setUnitOfMeasure(uom: UnitsOfMeasure)

    fun isNightMode(): Flow<Boolean>

    suspend fun toggleNightMode()

    private object PreferencesKeys {
        val nightMode = booleanPreferencesKey("dark_theme_enabled")
        val unitOfMeasure = stringPreferencesKey("unit_of_measure")
    }

    companion object {
        const val NAME: String = "weather_app_preferences"
    }
}