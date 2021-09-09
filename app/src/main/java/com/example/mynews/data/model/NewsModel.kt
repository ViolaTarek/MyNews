package com.example.mynews.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("urlToImage")
    val image: String = ""

)

