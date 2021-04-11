package stevemerollis.codetrial.weather.validate.ui

import stevemerollis.codetrial.weather.async.Model


sealed class ValidateViewState {

    object Loading: ValidateViewState()

    class Error(val model: Model.Error): ValidateViewState()

    class AcceptingInput(val currentInput: String): ValidateViewState()

    object CodeSubmitted: ValidateViewState()

    object PurchaseConfirmed: ValidateViewState()
}
