package com.example.medicatask.ui.main.newslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.medicatask.R
import com.example.medicatask.models.News
import com.example.medicatask.util.OnItemClickLisnter

class NewsItemVH(
    itemView: View,
    var glide: RequestManager,
    var onItemClick: OnItemClickLisnter
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        onItemClick.onClick(adapterPosition)
    }

    var titleTv: TextView = itemView.findViewById(R.id.title_tv)
    var sourceTv: TextView = itemView.findViewById(R.id.source_tv)
    var newsIamge: ImageView = itemView.findViewById(R.id.news_image)

    init {
        itemView.setOnClickListener(this)
    }
    fun bind(news: News) {
        titleTv.setText(news.title)
        sourceTv.setText(news.source.sourcename)
        glide.load(news.urlToImage).into(newsIamge)

    }

}