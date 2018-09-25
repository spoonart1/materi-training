package com.spoonart.training.feature.session_nine

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.spoonart.training.R
import com.spoonart.training.feature.session_six.LoginActivity
import com.spoonart.training.feature.session_four.AddItemActivity

class MainNavigationActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)

        initView()
    }

    private fun initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mViewPager = findViewById(R.id.content)

        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.mn_add -> {
                    startActivity(Intent(this, AddItemActivity::class.java))
                }

                R.id.mn_logout -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            }

            item.isChecked = true
            mDrawerLayout.closeDrawer(Gravity.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun setContent(){

    }
}