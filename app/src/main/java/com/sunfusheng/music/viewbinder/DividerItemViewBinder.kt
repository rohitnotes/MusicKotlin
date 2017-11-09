package com.sunfusheng.music.viewbinder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunfusheng.glideimageview.util.DisplayUtil
import com.sunfusheng.music.MainApplication
import com.sunfusheng.music.R
import com.sunfusheng.music.model.DividerItemModel
import me.drakeet.multitype.ItemViewBinder

/**
 * @author sunfusheng on 2017/11/2.
 */
class DividerItemViewBinder : ItemViewBinder<DividerItemModel, DividerItemViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_divider, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: DividerItemModel) {
        val layoutParams = holder.vDivider.layoutParams
        layoutParams.height = DisplayUtil.dip2px(MainApplication.context, item.height.toFloat())
        holder.vDivider.layoutParams = layoutParams

        val color = holder.vDivider.context.resources.getColor(item.colorResId)
        holder.vDivider.setBackgroundColor(color)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vDivider: View = view.findViewById(R.id.vDivider)
    }
}