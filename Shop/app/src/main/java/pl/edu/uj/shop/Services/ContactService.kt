package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.ContactInfo
import retrofit2.Call
import retrofit2.http.*

interface ContactService {
    @GET("contact")
    fun getContacts() : Call<List<ContactInfo>>

    @GET("contact/{id}")
    fun getContact(@Path("id") id: Int) : Call<ContactInfo>

    @POST("contact")
    fun createContact(@Body product: ContactInfo) : Call<ContactInfo>

    @DELETE("contact/{id}")
    fun deleteContact(@Path("id") id: Int) : Call<ContactInfo>
}