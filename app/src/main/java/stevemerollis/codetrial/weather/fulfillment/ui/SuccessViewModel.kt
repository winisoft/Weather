package stevemerollis.codetrial.weather.fulfillment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel
@Inject
constructor(): ViewModel() {

    private var _viewState: MutableLiveData<Unit?> = MutableLiveData<Unit?>(null)
    val viewState: LiveData<Unit?> = _viewState

    fun onSuccessFragmentVisible() {
        viewModelScope.launch {
            delay(VISIBLE_DURATION_MS)
            _viewState.value = Unit
        }
    }

    companion object {
        val TAG: String = SuccessViewModel::class.simpleName.toString()
        private const val VISIBLE_DURATION_MS: Long = 3000
    }

}