package com.example.flickraiexemple.domain.usercase.abs

import androidx.paging.PagingData
import com.example.flickraiexemple.domain.uimodel.PhotoUi
import kotlinx.coroutines.flow.Flow

interface FlickerHomeUseCase {
    fun fetchImages(): Flow<PagingData<PhotoUi>>
}
