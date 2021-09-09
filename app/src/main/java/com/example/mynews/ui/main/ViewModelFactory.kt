package com.example.mynews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews.data.repository.Repository

class ViewModelFactory  (private val repository: Repository,private val apiKey:String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(repository,apiKey) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}