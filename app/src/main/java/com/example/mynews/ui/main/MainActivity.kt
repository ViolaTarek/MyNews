package com.example.mynews.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynews.R
import com.example.mynews.data.model.NewsModel
import com.example.mynews.data.repository.Repository
import com.example.mynews.ui.adapters.NewsAdapter
import com.example.mynews.ui.details.NewsDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.activity_news_details.back_btn as back_btn1
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT


class MainActivity : AppCompatActivity(), ClickListener {


    private lateinit var mainViewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private var news: ArrayList<NewsModel> = ArrayList()

    var isback: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(arrayListOf(), this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        back_btn.setOnClickListener {
            mainViewModel.fetchNews()
        }
        swip_to_refresh_layout.setOnRefreshListener { mainViewModel.fetchNews() }
        search_editText?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    isback = true
                    var newsList: ArrayList<NewsModel> = ArrayList()
                    for (item in news) {
                        if (item.title.contains(v.text.toString())) {
                            newsList.add(item)
                        }
                    }
                    adapter.addData(newsList)
                    back_btn.visibility = View.VISIBLE
                    if (newsList.size == 0)
                        empty.visibility = View.VISIBLE
                    else
                        empty.visibility = View.GONE

                    return true
                }
                return false
            }
        });


    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isback)
            mainViewModel.fetchNews()
        else
            finish()
    }

    private fun setupObserver() {
        mainViewModel.getNews().observe(this, Observer {
            isback = false
            search_editText.text.clear()
            progressBar.visibility = View.GONE
            swip_to_refresh_layout.isRefreshing = false
            recyclerView.visibility = View.VISIBLE
            back_btn.visibility = View.GONE
            empty.visibility = View.GONE

            news.clear()
            news.addAll(it)
            adapter.addData(news)

        })
        mainViewModel.getErrors().observe(this, Observer {
            isback = false
            search_editText.text.clear()
            progressBar.visibility = View.GONE
            swip_to_refresh_layout.isRefreshing = false
            recyclerView.visibility = View.VISIBLE
            back_btn.visibility = View.GONE
            empty.visibility = View.GONE
            Toast.makeText(this,it,LENGTH_SHORT).show()
        })
    }

    override fun itemClicked(index: Int, newsModel: NewsModel) {
        val intent = Intent(this, NewsDetailsActivity::class.java).apply {
            putExtra("title", newsModel.title)
            putExtra("desc", newsModel.description)
            putExtra("image", newsModel.image)
        }
        startActivity(intent)
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(Repository(), getString(R.string.api_key))
        ).get(NewsViewModel::class.java)
    }

}
