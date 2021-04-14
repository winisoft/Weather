package stevemerollis.codetrial.weather.view

import android.view.View
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty


fun <T: View> child(
    id: Int
): ReadOnlyProperty<Fragment, T> = ReadOnlyProperty<Fragment, T> { thisRef, _ ->
    lazy { thisRef.view?.findViewById<T>(id) }.value!!
}
