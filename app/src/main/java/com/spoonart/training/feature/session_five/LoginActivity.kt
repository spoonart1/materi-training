package com.spoonart.training.feature.session_five

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.spoonart.training.R
import com.spoonart.training.feature.session_one.MainActivity
import com.spoonart.training.util.SharedUtil

class LoginActivity : AppCompatActivity() {

    var etUsername: EditText? = null
    var etPassword: EditText? = null
    var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    fun initView() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin!!.setOnClickListener {
            if (isValid()) {
                SharedUtil(this).save("is_logged_in", true)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    fun isValid(): Boolean {
        listOf(etUsername!!, etPassword!!).forEach {
            if (it.text.isNullOrEmpty()) {
                return false
            }
        }
        return true
    }

}

