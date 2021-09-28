package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelGraphql : Serializable {
    @SerializedName("shortcode_media")
    var shortcode_media: ModelShortcodeMedia? = null
}