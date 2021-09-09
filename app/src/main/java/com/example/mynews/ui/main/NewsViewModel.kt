package com.example.mynews.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynews.data.model.NewsModel
import com.example.mynews.data.repository.Repository

class NewsViewModel(private val mainRepository: Repository,private val key:String) : ViewModel() {

    private var news = MutableLiveData<ArrayList<NewsModel>>()
    private var error = MutableLiveData<String>()

    init {
        news = mainRepository.getNews()
        error = mainRepository.getErrors()
        mainRepository.getNews("eg",key)

    }

    fun fetchNews(){
        mainRepository.getNews("eg",key)
    }
    fun getNews(): MutableLiveData<ArrayList<NewsModel>> {
        return news
    }
    fun getErrors(): MutableLiveData<String> {
        return error
    }

}