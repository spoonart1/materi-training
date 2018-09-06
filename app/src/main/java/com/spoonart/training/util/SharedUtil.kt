package com.spoonart.training.util

import android.content.Context
import android.content.SharedPreferences

class SharedUtil(context: Context) {

    var preff: SharedPreferences
    var editor: SharedPreferences.Editor

    init {
        preff = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        editor = preff.edit()
    }

    fun save(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }

    fun getString(key: String): String {
        return preff.getString(key, "none")
    }

    fun getBool(key: String): Boolean {
        return preff.getBoolean(key, false)
    }
}