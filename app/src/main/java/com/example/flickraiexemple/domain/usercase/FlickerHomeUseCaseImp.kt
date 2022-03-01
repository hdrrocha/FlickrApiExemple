package com.example.flickraiexemple.domain.usercase

import com.example.flickraiexemple.domain.mapper.abs.FlickerHomeMapper
import com.example.flickraiexemple.domain.repository.FlickerHomeRepository
import com.example.flickraiexemple.domain.usercase.abs.FlickerHomeUseCase
import kotlinx.coroutines.flow.map

class FlickerHomeUseCaseImp(
    private val flickerHomeRepository: FlickerHomeRepository,
    private val mapper: FlickerHomeMapper
) : FlickerHomeUseCase {
    override fun fetchImages() = flickerHomeRepository.fetchImages().map { mapper.map(it) }
}


