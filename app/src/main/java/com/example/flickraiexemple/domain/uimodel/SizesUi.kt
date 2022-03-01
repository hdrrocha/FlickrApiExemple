package com.example.flickraiexemple.domain.uimodel


data class SizesUi(
    val canblog: Int,
    val canprint: Int,
    val candownload: Int,
    val size: MutableList<SizeUi?>
)
