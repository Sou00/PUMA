package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import io.realm.Realm
import io.realm.RealmConfiguration
import pl.edu.uj.shop.Models.*
import pl.edu.uj.shop.RealmModels.*
import pl.edu.uj.shop.Services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("test1.db")
            .schemaVersion(1)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }
    fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://661f-46-204-1-140.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productService = retrofit.create(ProductService::class.java)
        val productCall = productService.getProducts()

        productCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.code() == 200) {
                    val itemList = response.body()!!
                    itemList.forEach { item ->
                        val db = Realm.getDefaultInstance()
                        db.executeTransactionAsync {
                            val info = RealmProduct().apply {
                                this.id = item.id
                                this.desc = item.desc
                                this.name = item.name
                                this.price = item.price
                            }
                            it.insertOrUpdate(info)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            }
        })

        val faqService = retrofit.create(FAQService::class.java)
        val faqCall = faqService.getQuestions()

        faqCall.enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.code() == 200) {
                    val itemList = response.body()!!
                    itemList.forEach { item ->
                        val db = Realm.getDefaultInstance()
                        db.executeTransactionAsync {
                            val info = RealmQuestion().apply {
                                this.id = item.id
                                this.title = item.title
                                this.response = item.response
                            }
                            it.insertOrUpdate(info)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
            }
        })

        val contactService = retrofit.create(ContactService::class.java)
        val contactCall = contactService.getContacts()

        contactCall.enqueue(object : Callback<List<ContactInfo>> {
            override fun onResponse(call: Call<List<ContactInfo>>, response: Response<List<ContactInfo>>) {
                if (response.code() == 200) {
                    val itemList = response.body()!!
                    itemList.forEach { item ->
                        val db = Realm.getDefaultInstance()
                        db.executeTransactionAsync {
                            val info = RealmContactInfo().apply {
                                this.id = item.id
                                this.name = item.name
                                this.number = item.number
                                this.address = item.address
                            }
                            it.insertOrUpdate(info)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<ContactInfo>>, t: Throwable) {
            }
        })

        val userService = retrofit.create(UserService::class.java)
        val userCall = userService.getUsers()

        userCall.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.code() == 200) {
                    val itemList = response.body()!!
                    itemList.forEach { item ->
                        val db = Realm.getDefaultInstance()
                        db.executeTransactionAsync {
                            val info = RealmUser().apply {
                                this.id = item.id
                                this.name = item.name
                                this.age = item.age
                            }
                            it.insertOrUpdate(info)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
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