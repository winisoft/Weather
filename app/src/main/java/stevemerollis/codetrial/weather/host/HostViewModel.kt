package stevemerollis.codetrial.weather.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import stevemerollis.codetrial.weather.vehicle.Brand
import stevemerollis.codetrial.weather.vehicle.cal.brand.BrandUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HostViewModel
@Inject
constructor(
        val savedState: SavedStateHandle
): ViewModel() {


}