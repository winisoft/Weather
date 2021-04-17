package androidx.fragment.app

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
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
