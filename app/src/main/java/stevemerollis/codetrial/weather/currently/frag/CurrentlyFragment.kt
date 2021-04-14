package stevemerollis.codetrial.weather.currently.frag

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import com.github.matteobattilana.weather.WeatherView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.currently.CurrentlyContract
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.fragment.UI
import stevemerollis.codetrial.weather.fragment.WeatherFragment
import stevemerollis.codetrial.weather.view.*
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel
import javax.inject.Inject

@ActivityScoped
@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
class CurrentlyFragment
@Inject
constructor(
    val weatherView: WeatherView
) : WeatherFragment<
        CurrentlyFragment.CurrentlyModel,
        CurrentlyViewModel,
        CurrentlyFragment.Events
>(R.layout.fragment_currently) {

    override val userIntentFlow: Flow<UI.Event> = merge(flowOf(Events.ShowForecast))
        .onEach { vm.processIntent(it) }

    sealed class Events: UI.Event {
        object ShowForecast: Events()
        object Initial: Events()
        object Retry: Events()
    }

    data class CurrentlyModel(
        val condition: String,
        val icon: Drawable,
        val wind: String,
        val clouds: String,
        val visibility: String,
        val location: String,
        val reportedAt: String
    )

    override fun <M> render(model: M) {

    }


}