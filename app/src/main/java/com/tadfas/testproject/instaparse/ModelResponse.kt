package com.tadfas.testproject.instaparse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelResponse : Serializable {
    @SerializedName("graphql")
    var modelGraphql: ModelGraphql? = null
}