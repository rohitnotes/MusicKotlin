package com.sunfusheng.music.model

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicModel(val song_id: String, val title: String, val author: String) {

    override fun toString(): String {
        return "MusicModel(song_id='$song_id', title='$title', author='$author')"
    }
}