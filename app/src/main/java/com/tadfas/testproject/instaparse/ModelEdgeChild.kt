package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelEdgeChild : Serializable {
    @SerializedName("node")
    var modelNode: ModelNode? = null
}