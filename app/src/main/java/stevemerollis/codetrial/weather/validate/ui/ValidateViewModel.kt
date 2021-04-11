package stevemerollis.codetrial.weather.validate.ui

import androidx.lifecycle.*
import stevemerollis.codetrial.weather.validate.rules.application.RequestPasscodeUseCase
import stevemerollis.codetrial.weather.validate.rules.application.ConfirmPasscodeUseCase
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.host.HostState
import stevemerollis.codetrial.weather.fulfillment.access.app.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ValidateViewModel
@Inject
constructor(
        private val requestPasscode: RequestPasscodeUseCase,
        private val confirmPasscode: ConfirmPasscodeUseCase,
        private val orderUseCase: OrderUseCase,
        private val hostState: HostState
) : ViewModel(), LifecycleObserver {

    private val _display: MutableStateFlow<String> = MutableStateFlow("")
    private val display: StateFlow<String> = _display
    private val _viewState: MutableLiveData<ValidateViewState> = MutableLiveData(ValidateViewState.Loading)
    val viewState: LiveData<ValidateViewState> = _viewState

    fun handleButtonPress(button: UserInputEvent) {
        when (button) {
            is UserInputEvent.Go -> {
                viewModelScope.launch {
                    confirmPasscode(
                        this,
                        hostState.vin ?: throw Exception("NO VIN!"),
                        display.value
                    ).flowOn(CoroutineDsl.ui)
                    .collect {
                        when (it) {
                            is Model.Success ->  {
                                logD { "acquired priv access token" }
                                order(it.data.accessToken)
                            }
                           else -> {
                               _viewState.value = ValidateViewState.Error(it as Model.Error)
                               logE { "Failed to acquire priv access token" }
                           }
                        }
                    }
                }
            }
            is UserInputEvent.Resend -> with(this) {
                viewModelScope.launch {
                    orderStatus()
                }
            }
            is UserInputEvent.Delete -> {
                val input: String = _display.value.substring(0.._display.value.length - 2)
                _viewState.value = ValidateViewState.AcceptingInput(input)
                        .also { _display.value = it.currentInput }
            }
            else -> _viewState.value = ValidateViewState.AcceptingInput(_display.value.plus(button.value)
                        .also { _display.value = it })
        }
    }

    private suspend fun order(token: String) {

    }

    private suspend fun CoroutineScope.requestCode() {
        if (hostState.vin == null || hostState.otpTarget == null) {
            logE { "We screwed up, where are vin and code request body from contact method?"}
            _viewState.value = ValidateViewState.Error(Model.Error.Technical)
        } else {
            requestPasscode(this, hostState.vin!!, hostState.otpTarget!!)
                .flowOn(CoroutineDsl.ui)
                .collect {
                    when (it) {
                        is Model.Error.Technical, is Model.Error.Generic ->
                            _viewState.value = ValidateViewState.Error(it as Model.Error)
                        is Model.Success ->
                            _viewState.value = ValidateViewState.AcceptingInput(display.value)
                        else -> throw IllegalStateException("This is not a valid result of the code request.")
                    }
                }
        }
    }

    private suspend fun CoroutineScope.orderStatus() {

        _viewState.value = ValidateViewState.PurchaseConfirmed
//        submitPasscodeUseCase(codeInputValue).collect {
//            when (it) {
//                is Model.Success -> _viewState.value = ValidateViewState.PurchaseConfirmed
//                else -> _viewState.value = ValidateViewState.StateError(it as Model.Error)
//            }
//        }
    }

    init {
        viewModelScope.launch {
            requestCode()
        }
    }
}
