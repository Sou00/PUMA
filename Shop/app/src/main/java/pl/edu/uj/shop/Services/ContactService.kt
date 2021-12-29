package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.ContactInfo
import retrofit2.Call
import retrofit2.http.*

interface ContactService {
    @GET("product")
    fun getContacts() : Call<List<ContactInfo>>

    @GET("product/{id}")
    fun getContact(@Path("id") id: Int) : Call<ContactInfo>

    @POST("product")
    fun createContact(@Body product: ContactInfo) : Call<ContactInfo>

    @DELETE("product/{id}")
    fun deleteContact(@Path("id") id: Int) : Call<ContactInfo>
}