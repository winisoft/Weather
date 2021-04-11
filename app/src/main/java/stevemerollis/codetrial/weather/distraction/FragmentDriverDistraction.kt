package stevemerollis.codetrial.weather.distraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import stevemerollis.codetrial.weather.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton


@AndroidEntryPoint
class FragmentDriverDistraction: Fragment(R.layout.fragment_driver_distraction), LifecycleOwner {

    //@Inject lateinit var viewModelFactory: QuoteViewModelFactory
    //private val viewModel: QuoteViewModel by viewModels { viewModelFactory }
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
    }
}