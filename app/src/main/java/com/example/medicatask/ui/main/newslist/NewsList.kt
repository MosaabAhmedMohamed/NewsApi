package com.example.medicatask.ui.main.newslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.medicatask.R
import com.example.medicatask.models.News
import com.example.medicatask.util.OnItemClickLisnter
import com.example.medicatask.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsList : DaggerFragment(), OnItemClickLisnter {
    private val TAG = "NewsLIST"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: NewsListVM
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var newsAdapter: NewsListAdapter

    private lateinit var newsModels: List<News>

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this, providerFactory).get(NewsListVM::class.java)
        initList()
        observeNews()
    }

    private fun initList() {
        news_list_recycler.layoutManager = layoutManager
        newsAdapter.setLisnter(this)
        news_list_recycler.adapter = newsAdapter


    }

    private fun observeNews() {
        viewModel.observNews().removeObservers(viewLifecycleOwner)
        viewModel.observNews().observe(viewLifecycleOwner, Observer {
            newsModels = it.articles
            newsAdapter.setNews(it.articles)
        })
    }

    override fun onClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("model", newsModels.get(position))
        navController!!.navigate(R.id.action_news_list_to_news_detail,bundle)
    }

}