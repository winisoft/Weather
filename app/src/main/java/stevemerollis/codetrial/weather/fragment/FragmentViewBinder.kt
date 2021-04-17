package stevemerollis.codetrial.weather.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewBinding
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBinder<F, VB>
constructor(
    private val thisRef: F,
    private val binder: (View) -> VB)
: ReadOnlyProperty<F, VB>, DefaultLifecycleObserver where F: Fragment, VB: ViewBinding {

    override fun getValue(thisRef: F, property: KProperty<*>)
    : VB = FragmentViewBindingDelegate(thisRef, binder).getValue(thisRef, property)

    fun <T : ViewBinding> viewBinding(viewBindingFactory: (View) -> T) {
        FragmentViewBindingDelegate(thisRef, viewBindingFactory)
    }

}