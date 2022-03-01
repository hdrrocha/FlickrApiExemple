package com.example.flickraiexemple.domain.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.domain.mapper.abs.FlickerHomeMapper
import com.example.flickraiexemple.domain.uimodel.PhotoUi

class FlickerHomeMapperImp : FlickerHomeMapper {
    override fun map(photo: PagingData<Photo>) = photo.map { photo ->
        PhotoUi(
            id = photo.id,
            url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
            title = photo.title
        )
    }
}
