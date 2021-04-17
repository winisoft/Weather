package stevemerollis.codetrial.weather.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.R

abstract class BaseActivity: FragmentActivity() {

    private val entryPoint: BaseActivityEntryPoint
        get() = EntryPointAccessors.fromActivity(this, BaseActivityEntryPoint::class.java)

    private val BaseActivityEntryPoint.themeId: Flow<Int>
        get() = getPrefs().isNightMode().transform { emit(if (it) R.style.AppTheme_Dark else R.style.AppTheme_Light) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        EntryPointAccessors.fromActivity(this, BaseActivityEntryPoint::class.java).apply()
        super.onCreate(savedInstanceState, persistentState)
    }

    fun BaseActivityEntryPoint.apply() {
        supportFragmentManager.fragmentFactory = getFragmentInjector()
        lifecycleScope.launch {
            setTheme(themeId.single())
            themeId.onEach { setTheme(it); recreate() }.collect()
        }
    }
}