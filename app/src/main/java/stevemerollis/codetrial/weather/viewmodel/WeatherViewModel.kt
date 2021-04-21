@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.viewmodel

import android.os.Parcelable
import dagger.hilt.android.scopes.ViewModelScoped
import dispatch.android.viewmodel.DispatchViewModel
import dispatch.core.DefaultCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.ViewModelState
import stevemerollis.codetrial.weather.main.MainActivity
import stevemerollis.codetrial.weather.modelstore.ModelSubscriber
import stevemerollis.codetrial.weather.util.lo.logD
import java.io.IOException

@ExperimentalCoroutinesApi
@ViewModelScoped
abstract class WeatherViewModel<S>
: DispatchViewModel(), ModelSubscriber<S> {

    val _stateFlow = MutableStateFlow<S>(State.Init as S)

    abstract val stateFlow: StateFlow<S>

    abstract fun UseCase.Result<*>.map(): S


}