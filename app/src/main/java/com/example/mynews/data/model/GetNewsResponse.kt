package com.example.mynews.data.model

import com.google.gson.annotations.SerializedName

data class GetNewsResponse (
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalCount: Int = 0,
    @SerializedName("articles")
    val newsList: ArrayList<NewsModel> = ArrayList()
)