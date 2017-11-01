package com.sunfusheng.music

/**
 * @author sunfusheng on 2017/10/27.
 */
object Constants {

    const val BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/"

    const val METHOD_MUSIC_LIST = "ting?method=baidu.ting.billboard.billList"
    const val METHOD_DOWNLOAD_MUSIC = "ting?method=baidu.ting.song.play"
    const val METHOD_ARTIST_INFO = "ting?method=baidu.ting.artist.getInfo"
    const val METHOD_SEARCH_MUSIC = "ting?method=baidu.ting.search.catalogSug"
    const val METHOD_LRC = "ting?method=baidu.ting.song.lry"

    const val PARAM_METHOD = "method"
    const val PARAM_TYPE = "type"
    const val PARAM_SIZE = "size"
    const val PARAM_OFFSET = "offset"
    const val PARAM_SONG_ID = "songid"
    const val PARAM_TING_UID = "tinguid"
    const val PARAM_QUERY = "query"

    val REQUEST_SIZE = 10

    enum class MusicType(val type: Int) {
        NEW(1), //新歌
        HOT(2), //热歌
        BILLBOARD(8), //Billboard
        ROCK(11), //摇滚
        JAZZ(12), //爵士
        CHINESE(20), //华语金曲
        WESTERN(21), //欧美金曲
        CLASSIC(22), //经典老歌
        LOVER(23), //情歌对唱
        FILM(24), //影视金曲
        NET(25), //网络歌曲
    }

    val musicTypeList = listOf<MusicType>(
            MusicType.NEW,
            MusicType.HOT,
            MusicType.BILLBOARD,
            MusicType.ROCK,
            MusicType.JAZZ,
            MusicType.CHINESE,
            MusicType.WESTERN,
            MusicType.CLASSIC,
            MusicType.LOVER,
            MusicType.FILM,
            MusicType.NET
    )
}