package pl.edu.uj.shop.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pl.edu.uj.shop.R
import pl.edu.uj.shop.RealmModels.RealmContactInfo

class ContactInfoAdapter(private val context: Context,
                         private val dataSource: List<RealmContactInfo>
) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.list_item_contactinfo, parent, false)

        val titleTextView = rowView.findViewById(R.id.name) as TextView

        val subtitleTextView = rowView.findViewById(R.id.number) as TextView

        val detailTextView = rowView.findViewById(R.id.address) as TextView

        val contactInfo = getItem(position) as RealmContactInfo

        titleTextView.text = contactInfo.name
        subtitleTextView.text = contactInfo.address
        detailTextView.text = contactInfo.number

        return rowView
    }
}
