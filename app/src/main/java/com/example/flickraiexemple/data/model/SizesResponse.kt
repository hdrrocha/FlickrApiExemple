package com.example.flickraiexemple.data.model

import com.google.gson.annotations.SerializedName

data class SizesResponse(
    @SerializedName("sizes") val sizes: Sizes,
    @SerializedName("stat") val stat: String
)
