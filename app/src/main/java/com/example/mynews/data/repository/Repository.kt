package com.example.mynews.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mynews.data.api.ApiService
import com.example.mynews.data.model.GetNewsResponse
import com.example.mynews.data.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository{
    private val news = MutableLiveData<ArrayList<NewsModel>>()
    private val error = MutableLiveData<String>()

    fun getNews(country: String,key:String) {
        ApiService.create().getNews(country, key).enqueue(object : Callback<GetNewsResponse> {
            override fun onResponse(
                call: Call<GetNewsResponse>?,
                response: Response<GetNewsResponse>?
            ) {

                if (response?.body() != null)
                    news.postValue(response.body()!!.newsList)
                else
                    error.postValue("Server Error")
            }

            override fun onFailure(call: Call<GetNewsResponse>?, t: Throwable?) {
                error.postValue("Something went wrong!")
            }
        })
    }

    fun getNews(): MutableLiveData<ArrayList<NewsModel>> {
        return news
    }

    fun getErrors(): MutableLiveData<String> {
        return error
    }


}



