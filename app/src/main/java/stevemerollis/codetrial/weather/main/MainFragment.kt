package stevemerollis.codetrial.weather.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.R

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
constructor(

): Fragment(R.layout.fragment_main) {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}