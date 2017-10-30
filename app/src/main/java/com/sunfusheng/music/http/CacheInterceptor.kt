package com.sunfusheng.music.http

import com.sunfusheng.music.MainApplication
import com.sunfusheng.music.util.Util
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author sunfusheng on 2017/10/30.
 */
class CacheInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()

        if (!Util.isNetworkAvailable(MainApplication.context)) {
            // 没网强制从缓存读取
            request = request?.newBuilder()
                    ?.cacheControl(CacheControl.FORCE_CACHE)
                    ?.build()
        }

        val response = chain?.proceed(request!!)

        if (Util.isNetworkAvailable(MainApplication.context)) {
            // 有网时候读接口上的@Headers里的配置
            val cacheControl = request?.cacheControl().toString()
            return response?.newBuilder()
                    ?.removeHeader("Pragma")
                    ?.removeHeader("Cache-Control")
                    ?.header("Cache-Control", cacheControl)
                    ?.build()
        } else {
            val maxStale = 60 * 60 * 24 * 7 // 没网一周后失效
            return response?.newBuilder()
                    ?.removeHeader("Pragma")
                    ?.removeHeader("Cache-Control")
                    ?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    ?.build()
        }
    }
}