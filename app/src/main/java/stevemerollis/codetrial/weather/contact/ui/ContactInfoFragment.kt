package stevemerollis.codetrial.weather.contact.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.navController
import androidx.fragment.app.viewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.contact.domain.ContactMethod
import stevemerollis.codetrial.weather.databinding.FragmentContactInfoBinding
import stevemerollis.codetrial.weather.host.HostActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_error.*


@AndroidEntryPoint
class ContactInfoFragment: Fragment(R.layout.fragment_contact_info) {

    private val viewModel: ContactInfoViewModel by viewModels()
    private val view by viewBinding(FragmentContactInfoBinding::bind)
    private val infoAdapter: ContactInfoAdapter
            by lazy { ContactInfoAdapter(requireContext(), selectionCollector, viewModel.viewModelScope) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        logD { "displaying $TAG" }
        super.onCreateView(inflater, container, savedInstanceState)
        return view.root
    }

    private fun showContacts(contactInfos: List<ContactMethod>) = with(view) {
        btnCall.visibility = View.VISIBLE
        btnBack.visibility = View.VISIBLE
        infoAdapter.setData(contactInfos)
        infoAdapter.notifyDataSetChanged()
        rvContactInfo.apply {
            adapter = infoAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        }
        btnBack.setOnClickListener {
            navController.navigate(R.id.action_permissionFragment_to_fragmentQuote)
        }
        btnCall.setOnClickListener {
            (activity as HostActivity).requestPermission(HostActivity.AppPermission.PERMISSION_FINE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            with(view) {
            }
                //view.apply { } = View.GONE
                when (viewState) {
                    is ContactViewState.Error -> showError(viewState.errorModel)
                    is ContactViewState.ShowContacts -> showContacts(viewState.contactInfos)
                }
            }
        )
    }

    private val selectionCollector: (ContactMethod) -> Unit = {
        viewModel.hostState.otpTarget =  it
        navController.navigate(R.id.action_permissionFragment_to_validateFragment)
    }

    fun showError(model: Model.Error) {

        view?.apply {
            viewProgress.visibility = View.GONE

        }

        view?.viewProgress?.visibility = View.GONE


        when (model) {
            is Model.Error.Generic -> {


            }
            is Model.Error.Technical -> {
                tv_error_title.text = getString(R.string.error_technical_title)
                tv_error_message.text = getString(R.string.error_technical_message)
                btn_error_action.visibility = View.VISIBLE
                btn_error_action.text = getString(model.actionTextRes)
                btn_error_action.setOnClickListener { viewModel.retry() }
            }
            else -> throw IllegalStateException("Not a valid state for $TAG: $model")
        }
    }

    companion object {
        val TAG: String = ContactInfoFragment::class.simpleName!!
        const val ONSTAR_CALL_INTENT_ACTION = "com.gm.onstar.ONSTAR_CALL"
        const val KEY_ID_CALLING_CODE = "callingcode"
        const val ON_STAR_CALLING_CODE = 35
    }
}