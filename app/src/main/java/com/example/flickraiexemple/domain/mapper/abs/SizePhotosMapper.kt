package com.example.flickraiexemple.domain.mapper.abs

import com.example.flickraiexemple.data.model.SizesResponse
import com.example.flickraiexemple.domain.uimodel.SizesResponseUi

interface SizePhotosMapper {
    fun map(input: SizesResponse): SizesResponseUi
}
