package com.example.medicatask.ui.main.newslist.newsDetial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.medicatask.R
import com.example.medicatask.models.News
import com.example.medicatask.ui.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_detial.*
import javax.inject.Inject

class NewsDetail : DaggerFragment() {
    @Inject
    lateinit var gilde: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_news_detial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var news = arguments?.getParcelable<News>("model")

        gilde.load(news?.urlToImage).into(news_image)
        title_tv.setText(news?.title)
        desc_tv.setText(news?.description)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setActionBarTitle(getString(R.string.news_detail))
    }
}