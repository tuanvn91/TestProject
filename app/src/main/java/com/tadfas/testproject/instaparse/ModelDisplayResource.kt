package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelDisplayResource : Serializable {
    @SerializedName("src")
    var src: String? = null

    @SerializedName("config_width")
    var config_width = 0

    @SerializedName("config_height")
    var config_height = 0
}