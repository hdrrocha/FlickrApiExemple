package com.example.flickraiexemple.data.repository

import androidx.paging.*
import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.domain.repository.FlickerHomeRepository

class FlickerHomeRepositoryImp(
    private val pager: Pager<Int, Photo>
) : FlickerHomeRepository {
    companion object {
        const val DEFAULT_LIST_SIZE = 15
    }

    override fun fetchImages() = pager.flow
}
