package com.sunfusheng.music.http

import com.sunfusheng.music.Constants
import com.sunfusheng.music.MainApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author sunfusheng on 2017/10/27.
 */
class Api private constructor() {

    companion object {
        val instance = Api()
    }

    val apiService: ApiService

    init {
        val cacheDir = File(MainApplication.context.cacheDir, "HttpCache")
        val cache = Cache(cacheDir, 1024 * 1024 * 20)

        val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor())
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(CacheInterceptor())
                .addNetworkInterceptor(CacheInterceptor())
                .cache(cache)

        val retrofitBuilder = Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        apiService = retrofitBuilder.create(ApiService::class.java)
    }
}