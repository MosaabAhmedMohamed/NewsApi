package com.example.medicatask.ui.main

import android.os.Bundle
import com.example.medicatask.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }


    private fun init() {

    }

    fun setActionBarTitle(title: String)
    {
        title_tv.setText(title)
    }
}
