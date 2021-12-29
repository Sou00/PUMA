package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("user")
    fun getUsers() : Call<List<User>>

    @GET("user/{id}")
    fun getUser(@Path("id") id: Int) : Call<User>

    @POST("user")
    fun createUser(@Body product: User) : Call<User>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int) : Call<User>
}