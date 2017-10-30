package com.sunfusheng.music

/**
 * @author sunfusheng on 2017/10/27.
 */
object Constants {

    val BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/"

    val METHOD_MUSIC_LIST = "baidu.ting.billboard.billList"
    val METHOD_DOWNLOAD_MUSIC = "baidu.ting.song.play"
    val METHOD_ARTIST_INFO = "baidu.ting.artist.getInfo"
    val METHOD_SEARCH_MUSIC = "baidu.ting.search.catalogSug"
    val METHOD_LRC = "baidu.ting.song.lry"

    val PARAM_METHOD = "method"
    val PARAM_TYPE = "type"
    val PARAM_SIZE = "size"
    val PARAM_OFFSET = "offset"
    val PARAM_SONG_ID = "songid"
    val PARAM_TING_UID = "tinguid"
    val PARAM_QUERY = "query"
}