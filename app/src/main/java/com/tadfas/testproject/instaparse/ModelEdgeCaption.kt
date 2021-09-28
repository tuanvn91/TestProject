package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelEdgeCaption : Serializable {
    @SerializedName("node")
    var ModelNodeCaption: ModelNodeCaption? = null
}