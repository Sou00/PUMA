package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import pl.edu.uj.shop.Models.Product

class ProductList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val listView = findViewById<ListView>(R.id.listview)
        var list = arrayListOf<Product>()
        val productAdapter = ProductAdapter(this, list)
        listView.adapter = productAdapter

        listView.setOnItemClickListener { parent, view, position, id ->

        }
    }
}