package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelNode : Serializable {
    @SerializedName("display_url")
    var display_url: String? = null

    @SerializedName("display_resources")
    var display_resources: List<ModelDisplayResource>? = null

    @SerializedName("is_video")
    var isIs_video = false
        private set

    @SerializedName("video_url")
    var video_url: String? = null
    fun setIs_video(is_video: Boolean) {
        isIs_video = is_video
    }
}