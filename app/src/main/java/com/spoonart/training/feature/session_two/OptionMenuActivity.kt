package com.spoonart.training.feature.session_two

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.spoonart.training.R

class OptionMenuActivity : AppCompatActivity() {

    var tvTitle: TextView? = null
    var rootView: View? = null
    var tapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        rootView = findViewById(R.id.container)
        tvTitle = findViewById(R.id.tv_title)
        setTitleText("Halo Dunia")
    }

    private fun setTitleText(title: String) {
        tvTitle!!.text = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.mn_about -> {
                tapCount++
                showAlertBar("Toast: About", tapCount % 2 == 0)
            }
        }
        return false
    }

    fun showAlertBar(message: String, isToast: Boolean) {
        if (isToast) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            rootView?.let {
                Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}