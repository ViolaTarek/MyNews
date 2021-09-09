package com.example.mynews.data.api

import com.example.mynews.data.model.GetNewsResponse
import com.example.mynews.data.model.NewsModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("country", encoded = true) country: String,
        @Query("apiKey", encoded = true) apiKey: String
    ): Call<GetNewsResponse>

    companion object {

        var BASE_URL = "https://newsapi.org/v2/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }
}