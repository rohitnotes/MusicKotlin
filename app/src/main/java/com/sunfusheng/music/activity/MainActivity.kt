package com.sunfusheng.music.activity

import android.os.Bundle
import com.sunfusheng.music.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = """
            |for (c in "sunfusheng") {
            |    log(c)
            |}
        """.trimMargin("|")

        tv.text = text

    }
}
