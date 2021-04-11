package stevemerollis.codetrial.weather.request.ui

import android.content.Intent
import androidx.lifecycle.*
import stevemerollis.codetrial.weather.request.ui.OfferViewState.Error
import stevemerollis.codetrial.weather.request.access.GetOfferUseCase
import androidx.lifecycle.viewModelScope
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.host.HostState
import stevemerollis.codetrial.weather.request.OfferModel
import stevemerollis.codetrial.weather.fulfillment.Offer.Companion.KEY_INTENT_VIN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class OfferViewModel
@Inject
constructor(
        private val hostState: HostState,
        private val getOffer: GetOfferUseCase
) : ViewModel(),
LifecycleObserver {

    private var _viewState: MutableLiveData<OfferViewState> = MutableLiveData(OfferViewState.Loading)
    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, thr ->
        logE(thr) { thr.message ?: "Exception handled from view model" }
        _viewState.value = Error(Model.from(throwable = thr))
    }
    val context: CoroutineContext =
            viewModelScope.coroutineContext + handler + CoroutineName("QuoteVmScope")
    val viewState: LiveData<OfferViewState> = _viewState

    fun handleIntent(intent: Intent?) {
        if (hostState.offerModel != null) { //no need to fetch, we already loaded once before
            _viewState.value = OfferViewState.RenderUI(hostState.offerModel!!)
            return
        }

        if (intent?.getStringExtra(KEY_INTENT_VIN) == null) {
            _viewState.value = Error(Model.Error.InvalidArgs)
            return
        } else hostState.vin = intent.getStringExtra(KEY_INTENT_VIN)

        viewModelScope.launch(context + CoroutineDsl.ui) {
            getOffer(this, intent)
                    .flowOn(CoroutineDsl.bg)
                    .stateIn(viewModelScope + CoroutineDsl.ui)
                    .let {
                        when(it) {
                            is Model.Success<*> -> {
                                val data: OfferModel = (it.value as Model.Success<OfferModel>).data
                                _viewState.postValue(OfferViewState.RenderUI(data))
                                hostState.offerModel = data
                            }
                            is Model.Error -> {
                                val error: Model.Error = it.value as Model.Error
                                _viewState.postValue(Error(error))
                            }
                        }
                    }
        }
    }

    companion object {
        val TAG: String = OfferViewModel::class.simpleName!!
    }
}


