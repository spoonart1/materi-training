package com.spoonart.training.feature.session_six

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.spoonart.training.R
import com.spoonart.training.feature.session_three.ListActivity
import com.spoonart.training.util.SharedUtil

class LoginActivity : AppCompatActivity() {

    var etUsername: EditText? = null
    var etPassword: EditText? = null
    var btnLogin: Button? = null

    companion object {
        //clear or finish previous activity with FLAG Intent
        //rather than calling finish()
        //(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        fun start(from: Context) {
            from.startActivity(
                    Intent(from, LoginActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
    }

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
                //saving login session locally
                SharedUtil(this).save("is_logged_in", true)
                startActivity(Intent(this, ListActivity::class.java))
                finish()
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

