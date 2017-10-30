package com.sunfusheng.music.http

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * @author sunfusheng on 2017/10/27.
 */
interface ApiService {

    @Headers("Cache-Control: public, max-age=600")
    @GET("day/{year}/{month}/{day}")
    fun getGankDay(@Path("year") year: Int, @Path("month") month: Int, @Path("day") day: Int): Observable<String>

}