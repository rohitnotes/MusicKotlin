package com.sunfusheng.music.model

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicResponse(
        val song_list: List<MusicItemModel>,
        val billboard: BillboardModel,
        val error_code: Int
)