package com.sunfusheng.music.model

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicModel(
        val song_id: String,
        val title: String,
        val author: String,
        val album_title: String,
        val pic_big: String,
        val pic_small: String,
        val lrclink: String
)