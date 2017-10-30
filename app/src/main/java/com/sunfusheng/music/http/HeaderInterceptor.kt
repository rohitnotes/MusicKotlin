package com.sunfusheng.music.http

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author sunfusheng on 2017/10/30.
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request = chain?.request()
                ?.newBuilder()
                ?.addHeader("User-Agent", makeUA())
                ?.build()
        return chain?.proceed(request!!)
    }

    private fun makeUA(): String {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE
    }
}