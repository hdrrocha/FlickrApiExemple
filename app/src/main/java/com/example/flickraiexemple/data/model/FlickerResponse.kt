package com.example.flickraiexemple.data.model

import com.google.gson.annotations.SerializedName

data class FlickerResponse(
    @SerializedName("photos") val photos: Photos,
    @SerializedName("stat") val stat: String
)
