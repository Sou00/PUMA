package pl.edu.uj.shop

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import io.realm.Realm
import pl.edu.uj.shop.Models.*
import pl.edu.uj.shop.RealmModels.RealmContactInfo
import pl.edu.uj.shop.RealmModels.RealmProduct
import pl.edu.uj.shop.RealmModels.RealmQuestion
import pl.edu.uj.shop.RealmModels.RealmUser
import pl.edu.uj.shop.Services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    lateinit var retrofit: Retrofit

    fun buildRetrofit(baseUrl: String){
         retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCurrentData() {

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
                Log.w(ContentValues.TAG, "Loading products failed")

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
                Log.w(ContentValues.TAG, "Loading questions failed")

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
                Log.w(ContentValues.TAG, "Loading contacts failed")

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
                Log.w(ContentValues.TAG, "Loading users failed",t)

            }
        })


    }

    fun postOrder(order : Order){

        val orderService = retrofit.create(OrderService::class.java)
        val orderCall = orderService.createOrder(order)

        orderCall.enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                Log.i(ContentValues.TAG,"Posting order successful")
            }
            override fun onFailure(call: Call<Order>, t: Throwable) {
                Log.w(ContentValues.TAG, "Posting order failed",t)
            }
        })
    }
}