package com.example.medicatask.ui.main.newslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medicatask.network.list.NewsApi
import com.example.medicatask.network.list.NewsResource
import com.example.medicatask.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListVM @Inject constructor(var newsApi: NewsApi) : ViewModel() {
    private val disposable = CompositeDisposable()
    private var newsResources: MutableLiveData<NewsResource> = MutableLiveData()


    private val TAG: String = "NewsListVM"

    init {
        Log.d(TAG, "init")
    }

    fun observeNews(): LiveData<NewsResource> {
        newsResources.value = NewsResource("error",0, emptyList())
        disposable.add(newsApi.getNews()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ newsResources.value = it },{ newsResources.value!!.status = it.localizedMessage}))

        return newsResources
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposable.dispose()
    }
}