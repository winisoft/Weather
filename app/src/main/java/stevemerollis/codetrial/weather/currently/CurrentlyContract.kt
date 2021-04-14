package stevemerollis.codetrial.weather.currently

import android.graphics.drawable.Drawable
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.fragment.UI

interface CurrentlyContract {


//    data class ViewState(
//        val currently: CurrentlyModel?,
//        val isLoading: Boolean,
//        val error: Throwable?,
//        val isRefreshing: Boolean
//    ) {
//        companion object {
//            fun initial() = ViewState(
//                currently = null,
//                isLoading = true,
//                error = null,
//                isRefreshing = false
//            )
//        }
//    }
//
//    sealed class PartialChange {
//        abstract fun reduce(state: UI.State): UI.State
//        sealed class GetCurrently: PartialChange() {
//            override fun reduce(state: UI.State): UI.State = when (this) {
//                Loading -> viewState.copy(isLoading = true, error = null)
//                is Data -> viewState.copy(isLoading = false, error = null, currently = currently)
//                is Error -> viewState.copy(isLoading = false, error = error)
//            }
//            object Loading : GetCurrently()
//            data class Data(val currently: CurrentlyModel) : GetCurrently()
//            data class Error(val error: Throwable) : GetCurrently()
//        }
//
//        sealed class Refresh: PartialChange() {
//            override fun reduce(viewState: ViewState): ViewState = when (this) {
//                is Success -> viewState.copy(isRefreshing = false)
//                is Failure -> viewState.copy(isRefreshing = false)
//                Loading -> viewState.copy(isRefreshing = true)
//            }
//            object Loading: Refresh()
//            object Success: Refresh()
//            data class Failure(val error: Throwable): Refresh()
//        }
//    }
//
//    sealed class SingleEvent {
//        sealed class Fetch: Model() {
//            object Success: Refresh()
//            data class Failure(val error: Throwable): Refresh()
//        }
//        data class GetCurrentlyError(val error: Throwable): SingleEvent()
//    }
}