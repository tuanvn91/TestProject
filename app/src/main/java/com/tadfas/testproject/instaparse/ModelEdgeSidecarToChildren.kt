package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelEdgeSidecarToChildren : Serializable {
    @SerializedName("edges")
    var modelEdgeChildren: List<ModelEdgeChild>? = null
}