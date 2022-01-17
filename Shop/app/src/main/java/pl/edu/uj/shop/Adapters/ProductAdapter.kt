package pl.edu.uj.shop.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pl.edu.uj.shop.R
import pl.edu.uj.shop.RealmModels.RealmProduct

class ProductAdapter(private val context: Context,
                     private val dataSource: List<RealmProduct>
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

        val rowView = inflater.inflate(R.layout.list_item_product, parent, false)

        val titleTextView = rowView.findViewById(R.id.name) as TextView

        val subtitleTextView = rowView.findViewById(R.id.description) as TextView

        val detailTextView = rowView.findViewById(R.id.price) as TextView

        val product = getItem(position) as RealmProduct

        titleTextView.text = product.name
        subtitleTextView.text = product.desc
        detailTextView.text = product.price.toString()

        return rowView
    }
}
