package com.sunfusheng.music.widget

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.sunfusheng.music.R
import kotlinx.android.synthetic.main.layout_recyclerview.view.*
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author sunfusheng on 2017/10/20.
 */
class RecyclerViewWrapper : FrameLayout {

    private var errorView: View? = null
    private var emptyView: View? = null

    private var errorViewClickListener: OnClickListener? = null
    private var emptyViewClickListener: OnClickListener? = null
    private var onScrollListener: OnScrollListener? = null

    private lateinit var loadingStateDelegate: LoadingStateDelegate

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var multiTypeAdapter: MultiTypeAdapter

    private var firstVisibleItemPosition: Int = RecyclerView.NO_POSITION
    private var lastVisibleItemPosition: Int = RecyclerView.NO_POSITION

    constructor(context: Context) : super(context) {
        initView(context)
        initListener()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context) {}

    private fun initView(context: Context) {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_recyclerview, this)

        loadingStateDelegate = LoadingStateDelegate(recyclerView, loadingView, errorStub, emptyStub)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        multiTypeAdapter = MultiTypeAdapter()
        recyclerView.adapter = multiTypeAdapter
    }

    private fun initListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                onScrollListener?.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView?.layoutManager is LinearLayoutManager) {
                    firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                }
                onScrollListener?.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    fun <T> register(@NonNull clazz: Class<out T>, @NonNull binder: ItemViewBinder<T, *>) {
        multiTypeAdapter.register(clazz, binder)
    }

    fun setData(@NonNull items: MutableList<*>) {
        multiTypeAdapter.items = items
        multiTypeAdapter.notifyDataSetChanged()
    }

    fun getData(): List<*> {
        return multiTypeAdapter.items
    }

    fun notifyDataSetChanged() {
        multiTypeAdapter.notifyDataSetChanged()
    }

    fun setLoadingState(state: LoadingStateDelegate.State) {
        when (state) {
            LoadingStateDelegate.State.ERROR -> {
                if (recyclerView.adapter.itemCount > 0) {
                    loadingStateDelegate.setLoadingState(LoadingStateDelegate.State.SUCCEED)
                } else {
                    errorView = loadingStateDelegate.setLoadingState(LoadingStateDelegate.State.ERROR)
                    setErrorViewClickListener(errorViewClickListener)
                }
            }
            LoadingStateDelegate.State.EMPTY -> {
                emptyView = loadingStateDelegate.setLoadingState(LoadingStateDelegate.State.EMPTY)
                setEmptyViewClickListener(emptyViewClickListener)
            }
            else -> {
                loadingStateDelegate.setLoadingState(state)
            }
        }
    }

    fun setErrorViewClickListener(errorLayoutClickListener: View.OnClickListener?) {
        this.errorViewClickListener = errorLayoutClickListener
        errorView?.setOnClickListener(errorLayoutClickListener)
    }

    fun setEmptyViewClickListener(emptyViewClickListener: View.OnClickListener?) {
        this.emptyViewClickListener = emptyViewClickListener
        emptyView?.setOnClickListener(emptyViewClickListener)
    }

    fun setEmptyView(@LayoutRes layoutResource: Int) {
        emptyStub?.layoutResource = layoutResource
    }

    fun setErrorView(@LayoutRes layoutResource: Int) {
        errorStub.layoutResource = layoutResource
    }

    fun setOnScrollListener(onScrollListener: OnScrollListener) {
        this.onScrollListener = onScrollListener
    }

    interface OnScrollListener {
        fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int)

        fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
    }

}