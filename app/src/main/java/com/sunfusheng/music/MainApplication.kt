package com.sunfusheng.music

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

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

        val strategy = PrettyFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name))
                .showThreadInfo(false)
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(strategy))
    }
}