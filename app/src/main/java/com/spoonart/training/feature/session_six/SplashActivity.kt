package com.spoonart.training.feature.session_six

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.spoonart.training.R
import com.spoonart.training.feature.session_three.ListActivity
import com.spoonart.training.util.SharedUtil

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            gotoNext()
        }, 2000)
    }

    fun gotoNext() {
        if (!isLoggedIn()) {
            LoginActivity.start(this)
        } else {
            ListActivity.start(this)
        }
    }

    //retrieving login session from shared preference
    fun isLoggedIn(): Boolean {
        return SharedUtil(this).getBool("is_logged_in")
    }

}