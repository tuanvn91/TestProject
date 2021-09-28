package com.tadfas.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tadfas.testproject.instaparse.VideoModel

class ResultVideoAdapter :
    RecyclerView.Adapter<ResultVideoAdapter.VideoViewHolder>() {

    private var videos = mutableListOf<VideoModel>()

    fun setData(list: MutableList<VideoModel>){
        videos.clear()
        videos.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val item = videos[position]

        holder.title.text = item.caption.toString()
        Glide.with(holder.thumb.context)
            .load(item.thumbnail)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.thumb)


    }

    override fun getItemCount(): Int {
        return videos.size
    }


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val thumb: ImageView = itemView.findViewById(R.id.thumb)
        val tvDownload: TextView = itemView.findViewById(R.id.tv_download)
        val title: TextView = itemView.findViewById(R.id.title_video)
        val author: TextView = itemView.findViewById(R.id.author_video)
    }
}