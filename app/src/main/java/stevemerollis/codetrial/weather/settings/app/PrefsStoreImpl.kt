package stevemerollis.codetrial.weather.settings.app

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import stevemerollis.codetrial.weather.settings.app.PrefsStore.Companion.STORE_NAME
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsStoreImpl
@Inject constructor(
    @ApplicationContext val context: Context
) : PrefsStore {

    override fun isNightMode() = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
    }.map { prefs ->
        prefs[PreferencesKeys.nightMode] ?: false
    }

    override suspend fun toggleNightMode() {
        context.dataStore.edit {
            it[PreferencesKeys.nightMode] = !(it[PreferencesKeys.nightMode] ?: false)
        }
    }

    private object PreferencesKeys {
        val nightMode = booleanPreferencesKey("dark_theme_enabled")
    }
}

private val Context.dataStore by preferencesDataStore(STORE_NAME)