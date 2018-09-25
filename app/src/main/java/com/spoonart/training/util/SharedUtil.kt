package com.spoonart.training.util

import android.content.Context
import android.content.SharedPreferences

class SharedUtil(context: Context) {

    private var preff: SharedPreferences
    private var editor: SharedPreferences.Editor

    //a default method in kotlin when the class is initiated
    init {
        preff = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        editor = preff.edit()
    }

    //use commit to save synchronously -> main thread
    //use apply to save asynchronously -> background thread

    fun save(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getString(key: String): String {
        return preff.getString(key, "none")
    }

    fun getBool(key: String): Boolean {
        return preff.getBoolean(key, false)
    }
}


