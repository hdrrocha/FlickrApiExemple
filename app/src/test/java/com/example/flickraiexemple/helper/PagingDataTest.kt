package com.example.flickraiexemple.helper

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.ItemSnapshotList
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers

abstract class PagingDataTest {

    protected suspend fun <T : Any> getDifferSnapshot(
        data: PagingData<T>
    ): ItemSnapshotList<T> {
        val differ = AsyncPagingDataDiffer(
            diffCallback = DummyDiffCallback<T>(),
            updateCallback = DummyListUpdateCallback(),
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(data)

        return differ.snapshot()
    }
}
