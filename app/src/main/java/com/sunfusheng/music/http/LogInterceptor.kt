package com.sunfusheng.music.http

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * @author sunfusheng on 2017/10/30.
 */
class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request = chain?.request()
        Logger.d("log-api-request: " + request?.url().toString())

        val response = chain?.proceed(request!!)
        val contentType = response?.body()?.contentType()
        val content = response?.body()?.string()
        Logger.d("log-api-response: " + content)

        return response?.newBuilder()
                ?.body(ResponseBody.create(contentType, content!!))
                ?.build()
    }
}