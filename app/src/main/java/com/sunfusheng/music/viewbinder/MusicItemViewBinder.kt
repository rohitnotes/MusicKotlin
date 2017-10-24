package com.sunfusheng.music.viewbinder

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sunfusheng.music.R
import me.drakeet.multitype.ItemViewBinder

/**
 * @author sunfusheng on 2017/10/24.
 */
class MusicItemViewBinder : ItemViewBinder<String, MusicItemViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(@NonNull inflater: LayoutInflater, @NonNull parent: ViewGroup): ViewHolder {
        val view = inflater.inflate(R.layout.item_music, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, @NonNull item: String) {
        holder.tvTitle.text = item
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tv_title) as TextView
    }
}