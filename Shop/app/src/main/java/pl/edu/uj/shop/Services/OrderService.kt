package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.Order
import retrofit2.Call
import retrofit2.http.*

interface OrderService {
    @GET("order")
    fun getOrders() : Call<List<Order>>

    @GET("order/{id}")
    fun getOrder(@Path("id") id: Int) : Call<Order>

    @POST("order")
    fun createOrder(@Body product: Order) : Call<Order>

    @DELETE("order/{id}")
    fun deleteOrder(@Path("id") id: Int) : Call<Order>
}