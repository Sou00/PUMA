package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import io.realm.Realm
import io.realm.kotlin.delete
import pl.edu.uj.shop.Adapters.OrderAdapter
import pl.edu.uj.shop.Models.Product
import pl.edu.uj.shop.RealmModels.RealmOrder
import pl.edu.uj.shop.RealmModels.RealmProduct
import pl.edu.uj.shop.RealmModels.RealmUser

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        val listView = findViewById<ListView>(R.id.cartListView)

        val db = Realm.getDefaultInstance()
        val orderList = db.where(RealmOrder::class.java).findAll().toMutableList()
        val orderAdapter = OrderAdapter(this, orderList)
        listView.adapter = orderAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val order = orderAdapter.getItem(position) as RealmOrder
            val productId = order.productId

            if(order.quantity == 1) {
                orderList.removeAt(position)
                db.executeTransaction {
                    val orderToDelete = db.where(RealmOrder::class.java).equalTo("productId",productId).findFirst()
                    orderToDelete?.deleteFromRealm()
                }
                orderAdapter.notifyDataSetChanged()
            }
            else{
                val quantity = order.quantity
                db.executeTransaction {
                    val info = RealmOrder().apply {
                        this.id = 1
                        this.userId = 1
                        this.productId = productId
                        this.quantity = quantity - 1
                    }
                    it.insertOrUpdate(info)
                }
                orderAdapter.notifyDataSetChanged()
            }
        }
    }

}