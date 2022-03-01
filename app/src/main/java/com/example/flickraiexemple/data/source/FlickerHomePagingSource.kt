package com.example.flickraiexemple.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flickraiexemple.data.api.FlickerApi
import com.example.flickraiexemple.data.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickerHomePagingSource(val api: FlickerApi) : PagingSource<Int, Photo>() {

    companion object {
        const val INITIAL_START = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>) = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val start = params.key?.let { it + INITIAL_START } ?: INITIAL_START
        val limit = params.key?.let { it + params.loadSize } ?: params.loadSize

        val data = withContext(Dispatchers.IO) { api.fetchImages(start, limit) }

        return LoadResult.Page(
            data.photos.photo.orEmpty(),
            prevKey = if (start == INITIAL_START) null else start,
            nextKey = limit
        )
    }
}
