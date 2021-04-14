package stevemerollis.codetrial.weather.settings.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    fun isNightMode(): Flow<Boolean>

    suspend fun toggleNightMode()

    companion object {
        const val PREFS_NAME = ""
        const val STORE_NAME = "learning_data_store"
    }
}