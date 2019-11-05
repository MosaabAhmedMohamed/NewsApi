package com.example.medicatask.di.main

import com.example.medicatask.ui.main.newslist.NewsList
import com.example.medicatask.ui.main.newslist.newsDetial.NewsDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeNewsFragment(): NewsList?

    @ContributesAndroidInjector
    internal abstract fun contrbuteNewsDetailFragment(): NewsDetail?
}