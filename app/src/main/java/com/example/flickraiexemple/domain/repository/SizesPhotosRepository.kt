package com.example.flickraiexemple.domain.repository

import com.example.flickraiexemple.data.model.SizesResponse

interface SizesPhotosRepository {
    suspend fun getImageSize(idImage: String): SizesResponse
}
