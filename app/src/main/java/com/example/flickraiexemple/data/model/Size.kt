package com.example.flickraiexemple.data.model

import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("label") val label: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("source") val source: String,
    @SerializedName("url") val url: String,
    @SerializedName("media") val media: String
)
