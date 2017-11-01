package com.sunfusheng.music.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils

/**
 * @author sunfusheng on 2017/10/30.
 */
object Util {

    /**
     * 判断集合是否为空
     */
    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection == null || collection.isEmpty()
    }

    /**
     * 判断集合是否为非空
     */
    fun isNotEmpty(collection: Collection<*>): Boolean {
        return !isEmpty(collection)
    }

    /**
     * 判断网络是否可用
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo.isAvailable && networkInfo.state == NetworkInfo.State.CONNECTED
    }

    /**
     * 判断WiFi是否连接
     */
    fun isWiFiAvailable(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo.isAvailable && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 获取当前应用的版本号
     */
    fun getVersionName(context: Context): String {
        val packageManager = context.applicationContext.packageManager
        val packageInfo = packageManager.getPackageInfo(context.applicationContext.packageName, 0)
        val versionName = packageInfo.versionName
        return if (!TextUtils.isEmpty(versionName)) "V" + versionName else "V1.0"
    }

    /**
     * 获取当前应用的版本号
     */
    fun getVersionCode(context: Context): Int {
        val packageManager = context.applicationContext.packageManager
        val packageInfo = packageManager.getPackageInfo(context.applicationContext.packageName, 0)
        return packageInfo.versionCode
    }
}