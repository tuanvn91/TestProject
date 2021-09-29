package com.tadfas.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
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
        val caption = item.caption.toString()

        holder.title.text = caption
        Glide.with(holder.thumb.context)
            .load(item.thumbnail)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.thumb)


    }

    override fun getItemCount(): Int {
        return videos.size
    }


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val thumb: ImageView = itemView.findViewById(R.id.thumb)
        val capacity: TextView = itemView.findViewById(R.id.capacity)
        val title: TextView = itemView.findViewById(R.id.title_video)
        val quality: TextView = itemView.findViewById(R.id.quality)
    }
}