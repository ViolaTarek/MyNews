package com.example.mynews.ui.main

import com.example.mynews.data.model.NewsModel

interface ClickListener {
    fun itemClicked (index: Int,newsModel: NewsModel)
}