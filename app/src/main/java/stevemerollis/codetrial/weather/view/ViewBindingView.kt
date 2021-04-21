package stevemerollis.codetrial.weather.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ViewBindingView<T : ViewBinding>(
    val view: View,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Context, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Context, property: KProperty<*>): T {
        binding?.let { return it }

        return viewBindingFactory(view).also { this.binding = it }
    }
}