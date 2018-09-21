package com.spoonart.training.feature.session_one

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.spoonart.training.R

class MainActivity : AppCompatActivity() {

    var tvTitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        tvTitle = findViewById(R.id.tv_title)
        setTitleText("Halo Dunia")
    }

    private fun setTitleText(title: String) {
        tvTitle!!.text = title
    }



}