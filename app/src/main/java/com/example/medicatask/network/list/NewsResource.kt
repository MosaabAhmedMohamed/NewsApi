package com.example.medicatask.network.list

import com.example.medicatask.models.News
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResource(
    @SerializedName("status") @Expose var status: String,
    @SerializedName("totalResults") @Expose var totalResults: Int,
    @SerializedName("articles") @Expose var articles: List<News>
) {

    init {
        print("  ")
    }
}