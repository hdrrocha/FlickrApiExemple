package com.example.flickraiexemple.data.model

import com.google.gson.annotations.SerializedName

data class Sizes(
    @SerializedName("canblog") val canblog: Int,
    @SerializedName("canprint") val canprint: Int,
    @SerializedName("candownload") val candownload: Int,
    @SerializedName("size") val size: List<Size>
)

