package com.example.mycontactretrofit.app

import android.app.Application
import com.example.mycontactretrofit.BuildConfig
import com.example.mycontactretrofit.data.source.local.AppDatabase
import timber.log.Timber

class App:Application() {
    companion object{
        lateinit var instance:App
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        AppDatabase.init(this)
    }
}