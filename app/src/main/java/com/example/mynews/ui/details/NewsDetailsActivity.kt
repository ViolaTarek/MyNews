package com.example.mynews.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mynews.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.index_news.*
import kotlinx.android.synthetic.main.index_news.view.*

class NewsDetailsActivity : AppCompatActivity() {

    private var title: String = ""
    private var desc: String = ""
    private var image: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        getData()
        initUI()
    }

    fun getData() {
        title = intent.getStringExtra("title").toString()
        desc = intent.getStringExtra("desc").toString()
        image = intent.getStringExtra("image").toString()
    }

    fun initUI() {
        news_title_details.text = title
        if (desc != null) {
            news_desc_details.text = desc
            news_desc_details.visibility = View.VISIBLE

        } else
            news_desc_details.visibility = View.GONE
        Picasso.with(this)
            .load(image)
            .placeholder(R.drawable.placeholder)
            .into(news_image_details)
        back_btn.setOnClickListener { onBackPressed() }
    }
}
