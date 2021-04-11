package stevemerollis.codetrial.weather.currently.frag

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.currently.vm.CurrentlyViewModel
import stevemerollis.codetrial.weather.databinding.FragmentCurrentlyBinding
import stevemerollis.codetrial.weather.fragment.viewBinding

@AndroidEntryPoint
class CurrentlyFragment: Fragment(R.layout.fragment_currently) {

    private val viewModel: CurrentlyViewModel by viewModels()
    private val viewBinding: FragmentCurrentlyBinding by viewBinding(FragmentCurrentlyBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


}