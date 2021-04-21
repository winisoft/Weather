package android.view

import android.content.Context
import androidx.annotation.CheckResult
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import stevemerollis.codetrial.weather.view.ViewBindingView

inline fun <T : ViewBinding> View.viewBinding(crossinline bindingInflater: (View) -> T) =
    lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(this) }


@ExperimentalCoroutinesApi
@CheckResult
fun View.clicks(): Flow<View> {
    return callbackFlow {
        setOnClickListener { offer(it) }
        awaitClose { setOnClickListener(null) }
    }
}