package com.example.flickraiexemple.domain.usercase

import com.example.flickraiexemple.domain.mapper.abs.SizePhotosMapper
import com.example.flickraiexemple.domain.repository.SizesPhotosRepository
import com.example.flickraiexemple.domain.usercase.abs.SizesPhotosUseCase

class SizesPhotosUseCaseImp(
    private val repository: SizesPhotosRepository,
    private val mapper: SizePhotosMapper
) : SizesPhotosUseCase {
    override suspend fun getImageSize(idImage: String) =
        mapper.map(repository.getImageSize(idImage))
}

