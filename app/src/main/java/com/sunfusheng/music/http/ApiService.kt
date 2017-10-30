package com.sunfusheng.music.http

import com.sunfusheng.music.model.MusicResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author sunfusheng on 2017/10/27.
 */
interface ApiService {

    @Headers("Cache-Control: public, max-age=600")
    @GET("ting?method=baidu.ting.billboard.billList")
    fun getMusicList(@Query("type") type: Int, @Query("size") size: Int, @Query("offset") offset: Int): Observable<MusicResponse>

}