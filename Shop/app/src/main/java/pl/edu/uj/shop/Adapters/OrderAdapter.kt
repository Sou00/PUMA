package pl.edu.uj.shop.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import io.realm.Realm
import pl.edu.uj.shop.R
import pl.edu.uj.shop.RealmModels.RealmOrder
import pl.edu.uj.shop.RealmModels.RealmProduct
import pl.edu.uj.shop.RealmModels.RealmUser

class OrderAdapter(private val context: Context,
                   private val dataSource: List<RealmOrder>
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

        val order = getItem(position) as RealmOrder

        val productId = order.productId

        val db = Realm.getDefaultInstance()
        val product = db.where(RealmProduct::class.java).equalTo("id",productId).findFirst()!!
        //val user = db.where(RealmUser::class.java).equalTo("userId", order.userId).findFirst()!!

        titleTextView.text = product.name
        subtitleTextView.text = "Me"
        detailTextView.text = order.quantity.toString()

        return rowView
    }
}
