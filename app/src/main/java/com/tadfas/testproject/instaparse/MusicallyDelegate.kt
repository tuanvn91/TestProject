package com.tadfas.testproject.instaparse

import kotlin.collections.ArrayList

interface MusicallyDelegate {
    fun OnFailure(str: String?)
    fun OnSuccess(musicallyModel: ArrayList<VideoModel>)
}