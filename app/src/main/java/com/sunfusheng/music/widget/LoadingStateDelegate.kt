package com.sunfusheng.music.widget

import android.view.View
import android.view.ViewStub

/**
 * @author sunfusheng on 2017/10/24.
 */
class LoadingStateDelegate {

    enum class State(val value: Int) {
        SUCCEED(0),
        LOADING(1),
        ERROR(2),
        EMPTY(3)
    }

    private val viewHolder = arrayOfNulls<View>(4)
    private val viewStubHolder = arrayOfNulls<ViewStub>(4)

    constructor (normalView: View, loadingView: View, errorStub: ViewStub, emptyStub: ViewStub) {
        viewHolder[0] = normalView
        viewHolder[1] = loadingView
        viewStubHolder[2] = errorStub
        viewStubHolder[3] = emptyStub
    }

    fun setLoadingState(state: State): View? {
        if (state.value < 0 || state.value >= viewHolder.size) {
            return null
        }

        for (view in viewHolder) {
            view?.visibility = View.GONE
        }

        if (viewHolder[state.value] == null) {
            viewHolder[state.value] = viewStubHolder[state.value]?.inflate()
        }

        viewHolder[state.value]?.visibility = View.VISIBLE

        return viewHolder[state.value]
    }
}