package pl.edu.uj.shop.Services

import pl.edu.uj.shop.Models.Question
import retrofit2.Call
import retrofit2.http.*

interface FAQService {
    @GET("faq")
    fun getQuestions() : Call<List<Question>>

    @GET("faq/{id}")
    fun getQuestion(@Path("id") id: Int) : Call<Question>

    @POST("faq")
    fun createQuestion(@Body product: Question) : Call<Question>

    @DELETE("faq/{id}")
    fun deleteQuestion(@Path("id") id: Int) : Call<Question>
}