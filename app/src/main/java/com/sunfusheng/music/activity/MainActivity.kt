package com.sunfusheng.music.activity

import android.os.Bundle
import com.sunfusheng.music.R
import com.sunfusheng.music.viewbinder.MusicItemViewBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<String>()
        (1..50).mapTo(list) { it.toString() + ".song" }

        recyclerViewWrapper?.register(String::class.java, MusicItemViewBinder())
        recyclerViewWrapper?.setData(list)

    }

}
