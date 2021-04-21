@file:Suppress("UNCHECKED_CAST")

package stevemerollis.codetrial.weather.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stevemerollis.codetrial.weather.databinding.ActivityMainBinding.inflate
import stevemerollis.codetrial.weather.databinding.FragmentCurrentlyBinding
import stevemerollis.codetrial.weather.viewmodel.WeatherViewModel


@ExperimentalCoroutinesApi
abstract class WeatherFragment<V>(
    val factory: (View) -> V
) : Fragment() where V : ViewBinding {

    abstract fun viewEvents(): Flow<ViewEvent>

    abstract val viewBinding: V?

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    interface ViewEvent
}