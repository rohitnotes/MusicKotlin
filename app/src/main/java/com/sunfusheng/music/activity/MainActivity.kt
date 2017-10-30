package com.sunfusheng.music.activity

import android.os.Bundle
import android.util.Log
import com.sunfusheng.music.R
import com.sunfusheng.music.http.Api
import com.sunfusheng.music.viewbinder.MusicItemViewBinder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<String>()
        (1..50).mapTo(list) { it.toString() + ".song" }

        recyclerViewWrapper?.register(String::class.java, MusicItemViewBinder())
        recyclerViewWrapper?.setData(list)

        Api.instance.apiService.getMusicList(2, 10, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.song_list.forEach { Log.d("--->", it.toString()) }
                }, Throwable::printStackTrace)
    }

}
