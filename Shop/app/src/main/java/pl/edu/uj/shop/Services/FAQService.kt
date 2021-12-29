package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.Question
import retrofit2.Call
import retrofit2.http.*

interface FAQService {
    @GET("product")
    fun getQuestions() : Call<List<Question>>

    @GET("product/{id}")
    fun getQuestion(@Path("id") id: Int) : Call<Question>

    @POST("product")
    fun createQuestion(@Body product: Question) : Call<Question>

    @DELETE("product/{id}")
    fun deleteQuestion(@Path("id") id: Int) : Call<Question>
}