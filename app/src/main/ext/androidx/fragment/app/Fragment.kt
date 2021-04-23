package androidx.fragment.app

import androidx.annotation.CheckResult
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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
