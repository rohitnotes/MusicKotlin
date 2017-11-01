package com.sunfusheng.music.viewbinder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sunfusheng.glideimageview.GlideImageView
import com.sunfusheng.music.R
import com.sunfusheng.music.model.BillboardModel
import com.sunfusheng.music.util.ImageBlurUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.drakeet.multitype.ItemViewBinder

/**
 * @author sunfusheng on 2017/10/31.
 */
class MusicTitleViewBinder : ItemViewBinder<BillboardModel, MusicTitleViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_music_title, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: BillboardModel) {
        holder.ivPic.loadImage(item.pic_s192, R.color.placeholder)
                .listener { percent, isDone, exception ->
                    if (isDone) {
                        Observable.defer { Observable.just(ImageBlurUtil.blur(holder.ivPic.drawable)) }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ holder.ivPicBg.setImageBitmap(it) },
                                        Throwable::printStackTrace)
                    }
                }
        holder.tvMusicType.text = item.name
        holder.tvUpdateTime.text = "最近更新: " + item.update_date
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPic: GlideImageView = view.findViewById(R.id.ivPic)
        val ivPicBg: ImageView = view.findViewById(R.id.ivPicBg)
        val tvMusicType: TextView = view.findViewById(R.id.tvMusicType)
        val tvUpdateTime: TextView = view.findViewById(R.id.tvUpdateTime)
    }
}