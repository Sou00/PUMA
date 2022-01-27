package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import io.realm.Realm
import pl.edu.uj.shop.Adapters.OrderAdapter
import pl.edu.uj.shop.Models.Order
import pl.edu.uj.shop.RealmModels.RealmOrder
import pl.edu.uj.shop.RetrofitHelper.postOrder

class ShoppingCart : AppCompatActivity() {

    lateinit var db: Realm
    lateinit var orderAdapter: OrderAdapter
    lateinit var orderList: MutableList<RealmOrder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        val listView = findViewById<ListView>(R.id.cartListView)

        db = Realm.getDefaultInstance()
        orderList = db.where(RealmOrder::class.java).findAll().toMutableList()
        orderAdapter = OrderAdapter(this, orderList)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        orderList.forEach { order ->
            postOrder(Order(
                id = order.id,
                userId = order.userId,
                productId = order.productId,
                quantity = order.quantity
            ))
        }
        orderList.clear()
        db.executeTransaction {
            db.where(RealmOrder::class.java).findAll().deleteAllFromRealm()
        }
        orderAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }
}