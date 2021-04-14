package stevemerollis.codetrial.weather.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    private val aliveData = fragment.viewLifecycleOwnerLiveData

    private val whenClutteredTidyUp = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) { binding = null }
    }
    val aliveDataObserver = Observer<LifecycleOwner?> {
        (it ?: return@Observer).run { lifecycle.addObserver(whenClutteredTidyUp) }
    }
    private val aliveDataSelfawareObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            aliveData.observeForever(aliveDataObserver)
        }
        override fun onDestroy(owner: LifecycleOwner) {
            aliveData.removeObserver(aliveDataObserver)
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding
        ?.run { this }
        ?: fragment.viewLifecycleOwner
            .lifecycle
            .currentState
            .takeIf {
                it.isAtLeast(Lifecycle.State.INITIALIZED)
            }?.let {
                viewBindingFactory(thisRef.requireView()).also { binding = it }
            }
        ?: throw IllegalStateException(
            "Should not attempt to get bindings when Fragment views are destroyed.")

    init {
        fragment.lifecycle.addObserver(aliveDataSelfawareObserver)
    }
}