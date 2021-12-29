package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import okhttp3.OkHttpClient
import pl.edu.uj.shop.Services.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var productData:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun getCurrentData() {
        //productData = findViewById<ListView>(R.id.productListView)

        val retrofit = Retrofit.Builder()
            .baseUrl("url tutaj")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
        val service: ProductService = retrofit.create(ProductService::class.java)
        val call = service.getProducts()
        //val response = call.execute() //android.os.NetworkOnMainThreadException
       //if (response.isSuccessful){
       //    val list = response.body()
       //    val adapter = ProductAdapter(parent, list!!)
       //    productData.adapter = adapter
       //}
       //else{
       //    Log.d("xd","lipa")
       //}
    }

    fun loginClicked(view: View) {
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