package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.Order
import retrofit2.Call
import retrofit2.http.*

interface OrderService {
    @GET("product")
    fun getOrders() : Call<List<Order>>

    @GET("product/{id}")
    fun getOrder(@Path("id") id: Int) : Call<Order>

    @POST("product")
    fun createOrder(@Body product: Order) : Call<Order>

    @DELETE("product/{id}")
    fun deleteOrder(@Path("id") id: Int) : Call<Order>
}