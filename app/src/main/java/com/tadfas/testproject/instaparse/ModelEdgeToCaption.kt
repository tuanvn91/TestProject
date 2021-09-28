package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName

class ModelEdgeToCaption {
    @SerializedName("edges")
    var modelEdgeChildren: List<ModelEdgeCaption>? = null
}