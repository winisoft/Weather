package stevemerollis.codetrial.weather.request.terms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import stevemerollis.codetrial.weather.databinding.FragmentTermsDetailBinding


class TermsDetailFragment: Fragment() {

    val terms: NavArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        logD { "onCreateView: $TAG" }

        return FragmentTermsDetailBinding.inflate(inflater, container, false).run {
            tvTerms.text = terms.toString()
            root
        }
    }

    companion object {
        val TAG: String = TermsDetailFragment::class.simpleName!!
    }

}