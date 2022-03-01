package com.example.flickraiexemple.domain.usercase.abs

import com.example.flickraiexemple.domain.uimodel.SizesResponseUi

interface SizesPhotosUseCase {
    suspend fun getImageSize(idImage: String): SizesResponseUi
}
