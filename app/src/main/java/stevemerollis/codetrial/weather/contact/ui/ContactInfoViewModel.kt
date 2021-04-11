@file:Suppress("PrivatePropertyName")

package stevemerollis.codetrial.weather.contact.ui

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.*
import stevemerollis.codetrial.weather.contact.domain.GetContactInfoUseCase
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.host.HostState
import stevemerollis.codetrial.weather.vehicle.cal.brand.BrandUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class ContactInfoViewModel
@Inject
constructor(
        private val brandUtil: BrandUtil,
        private val getContactInfo: GetContactInfoUseCase,
        val hostState: HostState
) : ViewModel(),
  LifecycleObserver {

    private val _viewState: MutableLiveData<ContactViewState> = MutableLiveData(null)
    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, thr ->
        logE(thr) { thr.message ?: "Exception handled from view model" }
        _viewState.value = ContactViewState.Error(Model.from(throwable = thr))
    }
    private val coroContext: CoroutineContext =
        viewModelScope.coroutineContext + handler + CoroutineName("ContactInfoViewModel")

    val viewState: LiveData<ContactViewState> = _viewState
    val supportCallNumber: String = brandUtil.brand.supportNumber


    private val launchCallIntent: Intent = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:${brandUtil.brand.supportNumber}")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    fun retry() = collectContactInfos()

    private fun collectContactInfos() {
        viewModelScope.launch(context = coroContext + CoroutineDsl.bg) {
            getContactInfo(this)
            .collect {
                when (it) {
                    is Model.Success ->
                        safeSetMain(ContactViewState.ShowContacts(it.data))
                    else ->
                        safeSetMain(ContactViewState.Error(it as Model.Error))
                }
            }
        }
    }

    private suspend fun safeSetMain(viewState: ContactViewState) =
        withContext(coroContext + CoroutineDsl.ui) {
            _viewState.value = viewState
        }

    init {
        collectContactInfos()
    }

}