package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import pl.edu.uj.shop.Models.Product
import pl.edu.uj.shop.Services.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var productData: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productData = findViewById(R.id.productListView1)

    }
    fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://680e-2a02-a31e-4104-9900-a430-4285-704b-b8ca.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ProductService = retrofit.create(ProductService::class.java)
        val p = Product(13,"abc","asdasd",13.00)
        val call = service.getProducts()
        //<List<Product>
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.code() == 200) {
                    val productList = response.body()!!
                    //val productAdapter = ProductAdapter(parent,productList)
                    //productData!!.adapter = productAdapter
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            }
        })
    }

    fun loginClicked(view: View) {
        getCurrentData()
        val login = findViewById<EditText>(R.id.editTextPersonName)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
    fun registerClicked(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

}