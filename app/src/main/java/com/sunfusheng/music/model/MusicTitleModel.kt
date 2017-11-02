package com.sunfusheng.music.model

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicTitleModel(
        val billboard_type: String,
        val billboard_no: String,
        val update_date: String,
        val billboard_songnum: Int,
        val name: String,
        val comment: String,
        val pic_s192: String,
        val pic_s640: String,
        val pic_s444: String,
        val pic_s260: String,
        val pic_s210: String,
        val web_url: String
)