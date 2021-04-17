package stevemerollis.codetrial.weather.host

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HostViewModel
@Inject
constructor(
    val savedState: SavedStateHandle
): ViewModel() {


}