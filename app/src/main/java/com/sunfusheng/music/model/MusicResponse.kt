package com.sunfusheng.music.model

import java.util.*

/**
 * @author sunfusheng on 2017/10/30.
 */
data class MusicResponse(val song_list: Array<MusicModel>,
                         val billboard: BillboardModel,
                         val error_code: Int) {

    override fun toString(): String {
        return "MusicResponse(song_list=${Arrays.toString(song_list)}, billboard=$billboard, error_code=$error_code)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicResponse

        if (!Arrays.equals(song_list, other.song_list)) return false
        if (billboard != other.billboard) return false
        if (error_code != other.error_code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(song_list)
        result = 31 * result + billboard.hashCode()
        result = 31 * result + error_code
        return result
    }
}