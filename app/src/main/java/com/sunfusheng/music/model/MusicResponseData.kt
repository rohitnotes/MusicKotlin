package com.sunfusheng.music.model

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicResponseData(
        val song_list: List<MusicItemModel>,
        val billboard: MusicTitleModel,
        val error_code: Int
)