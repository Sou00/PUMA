package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import pl.edu.uj.shop.Models.Product

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        val listView = findViewById<ListView>(R.id.cartListView)
        var list = arrayListOf<Product>()
        val productAdapter = ProductAdapter(this, list)
        listView.adapter = productAdapter
    }

}