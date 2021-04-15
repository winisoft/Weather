package stevemerollis.codetrial.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.settings.app.WeatherPreferences

abstract class BaseActivityViewModel(
    private val prefsStore: WeatherPreferences,
) : ViewModel() {



    fun toggleNightMode() {
        viewModelScope.launch {
            prefsStore.toggleNightMode()
        }
    }
}