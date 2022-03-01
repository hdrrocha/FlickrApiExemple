package com.example.flickraiexemple.domain.mapper.abs

import androidx.paging.PagingData
import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.domain.uimodel.PhotoUi

interface FlickerHomeMapper {
    fun map(input: PagingData<Photo>): PagingData<PhotoUi>
}
