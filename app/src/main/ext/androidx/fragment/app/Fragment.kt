package androidx.fragment.app

import android.view.View
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import stevemerollis.codetrial.weather.fragment.FragmentViewBindingDelegate
import stevemerollis.codetrial.weather.view.ViewBindingDelegateFragment

val Fragment.navController get() = findNavController()

fun Lifecycle.onDestroyCall(block: (LifecycleOwner) -> Unit) = this.addObserver(
     object: DefaultLifecycleObserver {
             override fun onDestroy(owner: LifecycleOwner) {
                     super.onDestroy(owner)
                     block(owner)
     }
})

@ExperimentalCoroutinesApi
@CheckResult
fun Fragment.initializations(): Flow<Unit> {
    return callbackFlow {
        lifecycleScope.launchWhenCreated { offer(Unit) }
        awaitClose()
    }
}
