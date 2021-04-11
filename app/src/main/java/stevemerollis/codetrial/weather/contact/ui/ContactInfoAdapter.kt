package stevemerollis.codetrial.weather.contact.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.contact.domain.ContactMethod
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.android.synthetic.main.item_contact_info_new.view.*
import kotlinx.coroutines.CoroutineScope


class ContactInfoAdapter(
        @ActivityContext val context: Context,
        private val selectedItemCallback: (ContactMethod) -> Unit,
        private val coroutineScope: CoroutineScope
) : CoroutineScope by coroutineScope, RecyclerView.Adapter<ContactInfoAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    private var items: List<ContactMethod> = listOf()

    fun setData(list: List<ContactMethod>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = layoutInflater
        .inflate(R.layout.item_contact_info_new, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].apply {
            holder.bind(this, selectedItemCallback)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: View) : RecyclerView.ViewHolder(binding) {
        private val numberExplanation: String = binding.context.getString(R.string.mybrand_10_3_passcodeNumberEnding_csm)
        private val domainExplanation: String = binding.context.getString(R.string.mybrand_10_3_passcodeEmail_csm)

        fun bind(info: ContactMethod, callback: (ContactMethod) -> Unit): View = binding.apply {
            imgContactType.setBackgroundResource(
                if (info is ContactMethod.Phone) R.drawable.ic_mobiledevice else R.drawable.ic_email
            )
            tvContactInfo.text =
                    if (info is ContactMethod.Phone)
                        "$numberExplanation ${info.formatted}"
                    else
                        "$domainExplanation ${(info as ContactMethod.Email).formatted}"
            setOnClickListener {
               callback(info)
            }
        }

    }
}