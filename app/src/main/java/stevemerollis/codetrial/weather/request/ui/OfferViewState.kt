package stevemerollis.codetrial.weather.request.ui

import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.request.OfferModel


sealed class OfferViewState {

    object Loading: OfferViewState() {
        override fun toString(): String =  "QuoteViewState.Loading"
    }

    class Error(val value: Model.Error): OfferViewState() {
        override fun toString(): String =  "QuoteViewState.Error"
    }

    class RenderUI(val offerModel: OfferModel): OfferViewState() {
        override fun toString(): String =  "QuoteViewState.RenderUI"
    }

    object FinishApp: OfferViewState() {
        override fun toString(): String =  "QuoteViewState.FinishApp"
    }

}