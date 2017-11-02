package com.sunfusheng.music.http

import com.sunfusheng.music.Constants
import com.sunfusheng.music.model.MusicResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author sunfusheng on 2017/10/27.
 */
interface ApiService {

    @Headers("Cache-Control: public, max-age=600")
    @GET(Constants.METHOD_MUSIC_LIST)
    fun getMusicList(
            @Query(Constants.PARAM_TYPE) type: Int,
            @Query(Constants.PARAM_SIZE) size: Int,
            @Query(Constants.PARAM_OFFSET) offset: Int
    ): Observable<MusicResponseData>

}