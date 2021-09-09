package com.example.mynews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews.R
import com.example.mynews.data.model.NewsModel
import com.example.mynews.ui.main.ClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.index_news.view.*

class NewsAdapter(private val news: ArrayList<NewsModel>, private val listener: ClickListener) :
    RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(newsModel: NewsModel) {
            itemView.news_title.text = newsModel.title
            itemView.source.text = newsModel.source.name
            Picasso.with(itemView.context)
                .load(newsModel.image)
                .placeholder(R.drawable.placeholder)
                .into(itemView.news_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.index_news, parent,
                false
            )
        )

    override fun getItemCount(): Int = news.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(news[position])
        holder.itemView.setOnClickListener {
            listener.itemClicked(position, news[position])
        }
        //To change body of created functions use File | Settings | File Templates.
    }

    fun getList(): ArrayList<NewsModel> {
        return news
    }

    fun addData(list: List<NewsModel>) {
        news.clear()
        news.addAll(list)
        notifyDataSetChanged()

    }


}