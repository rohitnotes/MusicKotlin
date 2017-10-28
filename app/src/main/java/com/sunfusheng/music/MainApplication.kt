package com.sunfusheng.music

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author sunfusheng on 2017/10/27.
 */
class MainApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}