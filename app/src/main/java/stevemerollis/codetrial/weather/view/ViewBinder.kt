@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.view

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.fragment.WeatherFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

//@ExperimentalCoroutinesApi
//class ViewBinder<T>
//constructor(
//    val factory: (View) -> T)
//: ReadOnlyProperty<Fragment, T> where T: ViewBinding {
//
//
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
//        (thisRef as WeatherFragment<T>).viewBinding
//            ?: if (thisRef.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
//                factory(thisRef.requireView()).also { thisRef.viewBinding = it }
//            else throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
//
//
//    fun <T: ViewBinding> bind(fragment: WeatherFragment<T>) {
//
//        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            val viewLifecycleOwnerLiveDataObserver = Observer<LifecycleOwner?> {
//                val viewLifecycleOwner = it
//                    ?: return@Observer
//
//                viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
//                    override fun onDestroy(owner: LifecycleOwner) {
//                        fragment.viewBinding = null
//                    }
//                })
//            }
//
//            override fun onCreate(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
//            }
//
//            override fun onDestroy(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
//            }
//        })
//    }
//
//}