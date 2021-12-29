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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://b724-2a02-a31e-4104-9900-a430-4285-704b-b8ca.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ProductService = retrofit.create(ProductService::class.java)
        val call = service.getProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.code() == 200) {

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