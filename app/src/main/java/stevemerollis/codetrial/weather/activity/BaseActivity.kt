package stevemerollis.codetrial.weather.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stevemerollis.codetrial.weather.R

abstract class BaseActivity: FragmentActivity() {

    private val _darkThemeEnabled = MutableStateFlow<Boolean?>(null)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        EntryPointAccessors.fromActivity(this, BaseActivityEntryPoint::class.java).apply()

        super.onCreate(savedInstanceState, persistentState)

        _darkThemeEnabled.onEach { recreate() }.launchIn(lifecycleScope)
    }

    fun setTheme(darkThemeEnabled: Boolean) {
        this._darkThemeEnabled.value = darkThemeEnabled
    }

    fun BaseActivityEntryPoint.apply() {
        supportFragmentManager.fragmentFactory = getFragmentInjector()
        lifecycleScope.launch {
            getThemeFlow()
                .apply {
                    setTheme(if (single()) R.style.AppTheme_Dark else R.style.AppTheme_Light)
                }.onEach {
                    recreate()
                }
        }
    }
}