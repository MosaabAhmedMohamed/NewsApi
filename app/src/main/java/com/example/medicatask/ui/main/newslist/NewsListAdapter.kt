package com.example.medicatask.ui.main.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.medicatask.R
import com.example.medicatask.models.News
import com.example.medicatask.util.OnItemClickLisnter
import javax.inject.Inject

class NewsListAdapter @Inject constructor(var glide: RequestManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var news: List<News> = ArrayList()

    private lateinit var onItemClick: OnItemClickLisnter

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsItemVH) {
            holder.bind(news.get(position))
        }
    }

    override fun getItemCount(): Int = news.size

    fun setNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    fun setLisnter(onItemClick: OnItemClickLisnter) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsItemVH(view, glide, onItemClick)
    }
}