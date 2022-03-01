package com.example.flickraiexemple.domain.uimodel

data class PhotosUi(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: MutableList<PhotoUi?>
)

