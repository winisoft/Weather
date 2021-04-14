package stevemerollis.codetrial.weather.settings.fragment

import androidx.compose.ui.tooling.preview.UiMode
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import stevemerollis.codetrial.weather.settings.SharedPrefs
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment
@Inject
constructor(
    val sharedPrefs: SharedPrefs
) : Fragment() {


}