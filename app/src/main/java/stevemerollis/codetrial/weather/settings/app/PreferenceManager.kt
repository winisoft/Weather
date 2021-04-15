package stevemerollis.codetrial.weather.settings.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.catch
import okio.IOException
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.util.lo.logE
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class PreferenceManager
//@Inject
//constructor(
//    val dataStore: DataStore<WeatherPreferences>
//) : WeatherPreferences {
//
//    private fun <T> getData(mapper: (WeatherPreferences) -> T) = dataStore.data
//        .catch {
//            if (it is IOException) {
//                logE { "$TAG: IOException from getData" }
//                emit(getDefaultInstance())
//            } else {
//                throw it
//            }
//        }.mapper()
//
//    override fun getUnitOfMeasure(): UnitsOfMeasure = getData {
//        return@getData UnitsOfMeasure.Imperial
//    }
//
//    override suspend fun setUnitOfMeasure(uom: UnitsOfMeasure) {
//        dataStore.updateData { preferences ->
//            preferences.toBuilder()
//                .setType(uom)
//                .build()
//        }
//    }
//
//    override fun isNightMode(): Boolean = getData {
//        return@getData true
//    }
//
//    override suspend fun toggleNightMode() {
//        dataStore.edit {
//            it[PreferencesKeys.nightMode] = !(it[PreferencesKeys.nightMode] ?: false)
//        }
//    }
//
//    private object PreferencesKeys {
//        val nightMode = booleanPreferencesKey("dark_theme_enabled")
//    }
//
//    companion object {
//        private val TAG: String = PreferenceManager::class.simpleName.toString()
//        private const val WEATHER_PREFERENCES_NAME = "weather_preferences"
//        const val DATA_STORE_FILE_NAME = "weather_prefs.pb"
//        private const val UNIT_OF_MEASURE_KEY = "unit_of_measure"
//    }
//}