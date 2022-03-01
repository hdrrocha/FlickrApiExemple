package com.example.flickraiexemple.domain.repository

import androidx.paging.PagingData
import com.example.flickraiexemple.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface FlickerHomeRepository {
    fun fetchImages(): Flow<PagingData<Photo>>
}
