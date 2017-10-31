package com.sunfusheng.music.activity

import android.os.Bundle
import com.sunfusheng.music.R
import com.sunfusheng.music.http.Api
import com.sunfusheng.music.model.MusicModel
import com.sunfusheng.music.viewbinder.MusicItemViewBinder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewWrapper?.register(MusicModel::class.java, MusicItemViewBinder())

        Api.instance.apiService.getMusicList(2, 20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    recyclerViewWrapper.setData(it.song_list)
                }, Throwable::printStackTrace)
    }

}
