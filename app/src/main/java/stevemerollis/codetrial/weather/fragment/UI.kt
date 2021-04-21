package stevemerollis.codetrial.weather.fragment

import androidx.viewbinding.ViewBinding
import dispatch.android.viewmodel.DispatchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel

//
//interface Element {
//
//    val state: Flow<State>
//
//    val intentions: Channel<Intentions>
//
//    interface State
//
//    interface Intentions
//
//}
//
//@OptIn(ExperimentalCoroutinesApi::class)
//interface UI<V, M, S> where V: ViewBinding, M: WeatherViewModel, S: WeatherViewModel.State {
//
//    val viewBinding: V?
//
//    val viewModel: M
//
//    fun V.render(state: S)
//
//}
//
//interface VM: Element {
//    override val state: StateFlow<Element.State>
//}
//
//interface UC: Element
//
//interface Repo: Element
//
//interface Data: Element {
//
//}