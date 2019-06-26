package com.brianvega.startupweekend

import android.app.Application
import android.content.Context

class MainApplication: Application() {

    private lateinit var context: Context
    private lateinit var instance: MainApplication

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = instance.context

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        
    }

    fun getInstance(): MainApplication = instance

    fun getContext(): Context = context

}