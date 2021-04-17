@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

@ExperimentalCoroutinesApi
abstract class WeatherFragment<T>(private val viewFactory: (View) -> T)
: Fragment(),
    UI,
    DefaultLifecycleObserver,
    ReadOnlyProperty<WeatherFragment<T>, T> where T: ViewBinding {

    protected var viewBinding = viewFactory.run { with(view ?: return@run, this) } as T?

    override val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateFlow
            .onEach { render(viewBinding ?: return@onEach, it) }
            .launchIn(lifecycleScope)
    }

    ///region *** View Binding Delegate ***
    private val whenClutteredTidyUp = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) { viewBinding = null }
    }
    val lifecycleObserver = Observer<LifecycleOwner?> {
        (it ?: return@Observer).run { lifecycle.addObserver(whenClutteredTidyUp) }
    }
    private val lifecycleLiveDataObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            viewLifecycleOwnerLiveData.observeForever(lifecycleObserver)
        }
        override fun onDestroy(owner: LifecycleOwner) {
            viewLifecycleOwnerLiveData.removeObserver(lifecycleObserver)
        }
    }

    override fun getValue(thisRef: WeatherFragment<T>, property: KProperty<*>): T = viewBinding
        ?: viewLifecycleOwner
            .lifecycle
            .currentState
            .takeIf {
                it.isAtLeast(Lifecycle.State.INITIALIZED)
            }?.let {
                viewBinding = viewFactory(thisRef.requireView()) as T
                return@let viewBinding
            }
        ?: throw IllegalStateException("Attempted to get bindings when Fragment views are destroyed.")

    init {
        lifecycle.addObserver(lifecycleLiveDataObserver)
    }

    ///endregion *** View Binding Delegate ***
}