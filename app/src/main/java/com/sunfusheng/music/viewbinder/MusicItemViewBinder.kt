package com.sunfusheng.music.viewbinder

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sunfusheng.glideimageview.GlideImageView
import com.sunfusheng.music.R
import com.sunfusheng.music.model.MusicItemModel
import me.drakeet.multitype.ItemViewBinder

/**
 * @author sunfusheng on 2017/10/24.
 */
class MusicItemViewBinder : ItemViewBinder<MusicItemModel, MusicItemViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(@NonNull inflater: LayoutInflater, @NonNull parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_music, parent, false))
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, @NonNull item: MusicItemModel) {
        holder.tvTitle.text = item.title
        holder.tvAuthor.text = holder.tvAuthor.resources.getString(R.string.music_author_album, item.author, item.album_title)
        holder.ivPic.loadImage(item.pic_big, R.color.placeholder)
        holder.ivMore.setOnClickListener({})
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPic: GlideImageView = view.findViewById(R.id.ivPic)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        val ivMore: ImageView = view.findViewById(R.id.ivMore)
    }
}