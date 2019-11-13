package com.example.medicatask.ui.main.newslist

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.medicatask.R
import com.example.medicatask.models.News
import com.example.medicatask.ui.main.MainActivity
import com.example.medicatask.util.LoadingDialog
import com.example.medicatask.util.OnItemClickLisnter
import com.example.medicatask.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsList : DaggerFragment(), OnItemClickLisnter, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        stopRefreshLayout()
        observeNews()
    }

    private val TAG = "NewsLIST"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: NewsListVM
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var newsAdapter: NewsListAdapter

    private var progressDialog: ProgressDialog? = null


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
        initRefreshLayout()
        initList()
        observeNews()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setActionBarTitle(getString(R.string.news_list))
    }

    private fun initRefreshLayout() {
        swipe_refresh_layout.setColorSchemeResources(
            android.R.color.black,
            android.R.color.holo_red_light,
            android.R.color.darker_gray
        )
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    private fun stopRefreshLayout() {
        if (swipe_refresh_layout.isRefreshing)
            swipe_refresh_layout.isRefreshing = false
    }

    private fun initList() {
        news_list_recycler.layoutManager = layoutManager
        newsAdapter.setLisnter(this)
        news_list_recycler.adapter = newsAdapter
    }

    private fun observeNews() {
        showProgress()
        stopRefreshLayout()
        viewModel.observeNews().removeObservers(viewLifecycleOwner)
        viewModel.observeNews().observe(viewLifecycleOwner, Observer {
            hideProgress()
            if (it.status.equals("ok")) {
                newsModels = it.articles
                newsAdapter.setNews(it.articles)
            } else
                Log.i(TAG, it.status)
        })
    }

    fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    fun showProgress() {
        hideProgress()
        progressDialog = activity?.let { LoadingDialog.showLoadingDialog(it) }
    }

    override fun onClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("model", newsModels.get(position))
        navController!!.navigate(R.id.action_news_list_to_news_detail, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (news_list_recycler.layoutManager != null) {
            news_list_recycler.layoutManager = null
        }
    }

}