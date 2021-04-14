package stevemerollis.codetrial.weather.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.fragment.UI

@ExperimentalCoroutinesApi
@ViewModelScoped
abstract class PaymentViewModel<S: UI.State>
: ViewModel(),
  AbstractWeatherViewModel<S>
{


}