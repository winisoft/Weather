package stevemerollis.codetrial.weather.settings.app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadOnlyProperty

@Singleton
class PreferenceManager
@Inject
constructor(
    private val prefsStore: DataStore<Preferences>
): Prefs,
    DataStore<Preferences> by prefsStore {

    private fun <T> read(key: Preferences.Key<T>, default: T) = prefsStore.data
        .catch { cause ->
            if (cause is IOException) {
                emit(emptyPreferences())
            } else {
                throw cause
            }
    }.map { it[key] ?: default }

    override fun getUnitOfMeasure(): Flow<UnitsOfMeasure> =
        read(PreferencesKeys.unitOfMeasure, UnitsOfMeasure.Standard.key).map { UnitsOfMeasure.fromKey(it) }

    override suspend fun setUnitOfMeasure(uom: UnitsOfMeasure) {
        prefsStore.edit {
            it[PreferencesKeys.unitOfMeasure] = uom.key
        }
    }

    override fun isNightMode(): Flow<Boolean> = read(PreferencesKeys.nightMode, false)

    override suspend fun toggleNightMode() {
        prefsStore.edit {
            it[PreferencesKeys.nightMode] = !(it[PreferencesKeys.nightMode] ?: false)
        }
    }

    private object PreferencesKeys {
        val nightMode = booleanPreferencesKey("dark_theme_enabled")
        val unitOfMeasure = stringPreferencesKey("unit_of_measure")
    }

    companion object {
        private val TAG: String = PreferenceManager::class.simpleName.toString()
        private const val WEATHER_PREFERENCES_NAME = "weather_preferences"
        const val DATA_STORE_FILE_NAME = "weather.preferences_pb"
        private const val UNIT_OF_MEASURE_KEY = "unit_of_measure"
    }

}