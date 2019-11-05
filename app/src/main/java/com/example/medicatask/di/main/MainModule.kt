package com.example.medicatask.di.main

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.medicatask.network.list.NewsApi
import com.example.medicatask.ui.main.newslist.NewsListAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {


    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsListAdapter(glide: RequestManager): NewsListAdapter {
        return NewsListAdapter(glide)
    }

    @Provides
    fun provideLayoutManager(context: Application): RecyclerView.LayoutManager {
        return LinearLayoutManager(context.applicationContext)
    }
}