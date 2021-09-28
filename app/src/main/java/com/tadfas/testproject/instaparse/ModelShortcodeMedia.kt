package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelShortcodeMedia : Serializable {
    @SerializedName("display_url")
    var display_url: String? = null

    @SerializedName("display_resources")
    var display_resources: List<ModelDisplayResource>? = null

    @SerializedName("is_video")
    var isIs_video = false
        private set

    @SerializedName("video_url")
    var video_url: String? = null

    @SerializedName("edge_sidecar_to_children")
    var edge_sidecar_to_children: ModelEdgeSidecarToChildren? = null

    @SerializedName("edge_media_to_caption")
    var edge_media_to_caption: ModelEdgeToCaption? = null



    fun setIs_video(is_video: Boolean) {
        isIs_video = is_video
    }
}