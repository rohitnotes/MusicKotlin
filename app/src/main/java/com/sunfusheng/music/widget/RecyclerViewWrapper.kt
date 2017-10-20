package com.sunfusheng.music.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.sunfusheng.music.R
import kotlinx.android.synthetic.main.layout_recyclerview.view.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author sunfusheng on 2017/10/20.
 */
class RecyclerViewWrapper : FrameLayout {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var multiTypeAdapter: MultiTypeAdapter

    constructor(context: Context) : super(context) {
        initView(context)
        initListener()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context) {}

    private fun initView(context: Context) {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_recyclerview, this)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        multiTypeAdapter = MultiTypeAdapter()
        recyclerView.adapter = multiTypeAdapter
    }

    private fun initListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}