package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import io.realm.Realm
import pl.edu.uj.shop.Adapters.ProductAdapter
import pl.edu.uj.shop.RealmModels.RealmOrder
import pl.edu.uj.shop.RealmModels.RealmProduct

class ProductList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val listView = findViewById<ListView>(R.id.productListView)
        val db = Realm.getDefaultInstance()
        val productList = db.where(RealmProduct::class.java).findAll().toList()
        val productAdapter = ProductAdapter(this, productList)
        listView.adapter = productAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val product = productAdapter.getItem(position) as RealmProduct
            val productId = product.id
            val order = db.where(RealmOrder::class.java).equalTo("productId", productId).findFirst()

            if(order == null) {
                db.executeTransactionAsync {
                    val info = RealmOrder().apply {
                        this.id = 1
                        this.userId = 1
                        this.productId = productId
                        this.quantity = 1
                    }
                    it.insertOrUpdate(info)
                }
            }
           else{
               val quantity = order.quantity
               db.executeTransactionAsync {
                   val info = RealmOrder().apply {
                       this.id = 1
                       this.userId = 1
                       this.productId = productId
                       this.quantity = quantity + 1
                   }
                   it.insertOrUpdate(info)
               }
           }
        }
    }
}