package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelNodeCaption: Serializable {
    @SerializedName("text")
    var text : String? = null
}