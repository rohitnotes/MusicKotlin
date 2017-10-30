package com.sunfusheng.music.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils

/**
 * @author sunfusheng on 2017/10/30.
 */
object Util {

    // 判断网络是否可用
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivity = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return false
    }

    // WiFi是否连接
    fun isWiFiAvailable(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    // 获取当前应用的版本号
    fun getVersionName(context: Context): String {
        try {
            val packageManager = context.applicationContext.packageManager
            val packInfo = packageManager.getPackageInfo(context.applicationContext.packageName, 0)
            val version = packInfo.versionName
            if (!TextUtils.isEmpty(version)) {
                return "V" + version
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "V1.0"
    }

    // 获取当前应用的版本号
    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.applicationContext.packageManager
            val packInfo = packageManager.getPackageInfo(context.applicationContext.packageName, 0)
            return packInfo.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 1
    }
}