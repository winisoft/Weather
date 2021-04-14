package stevemerollis.codetrial.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.settings.app.PrefsStore
import javax.inject.Inject

abstract class BaseActivityViewModel(
    private val prefsStore: PrefsStore,
) : ViewModel() {



    fun toggleNightMode() {
        viewModelScope.launch {
            prefsStore.toggleNightMode()
        }
    }
}