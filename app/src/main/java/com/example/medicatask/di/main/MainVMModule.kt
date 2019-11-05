package com.example.medicatask.di.main

import androidx.lifecycle.ViewModel
import com.example.medicatask.di.ViewModelKey
import com.example.medicatask.ui.main.newslist.NewsListVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListVM::class)
    abstract fun bindNewsListVM(newsListVM: NewsListVM): ViewModel
}