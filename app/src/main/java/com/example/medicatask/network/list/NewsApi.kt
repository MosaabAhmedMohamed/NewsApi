package com.example.medicatask.network.list

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getNews(@Query("apiKey") apiKey: String, @Query("country") country: String): Observable<NewsResource>

    @GET("news_list")
    fun getNews(): Observable<NewsResource>

}