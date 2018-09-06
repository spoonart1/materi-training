package com.spoonart.training.base

import android.app.Application
import com.orm.SugarContext

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SugarContext.init(this)
    }
}