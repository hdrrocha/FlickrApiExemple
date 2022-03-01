package com.example.flickraiexemple.data.repository

import com.example.flickraiexemple.data.api.SizesApi
import com.example.flickraiexemple.domain.repository.SizesPhotosRepository

class SizesPhotosRepositoryImp(private val api: SizesApi) : SizesPhotosRepository {
    override suspend fun getImageSize(idImage: String) = api.getImageSize(idImage)
}
