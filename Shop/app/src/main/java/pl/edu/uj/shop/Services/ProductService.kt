package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.Product
import retrofit2.Call
import retrofit2.http.*

interface ProductService {
    @GET("product")
    fun getProducts() : Call<List<Product>>

    @GET("product/{id}")
    fun getProduct(@Path("id") id: Int) : Call<Product>

    @POST("product")
    fun createProduct(@Body product: Product) : Call<Product>

    @DELETE("product/{id}")
    fun deleteProduct(@Path("id") id: Int) : Call<Product>
}