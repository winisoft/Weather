package stevemerollis.codetrial.weather.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseActivity: FragmentActivity() {

    private val _darkThemeEnabled = MutableStateFlow<Boolean?>(null)
    val darkThemeEnabled: StateFlow<Boolean?> get() = _darkThemeEnabled

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        EntryPointAccessors.fromActivity(this, FragmentInjectorEntryPoint::class.java)
        super.onCreate(savedInstanceState, persistentState)
    }

}