package com.example.flickraiexemple.data.model

import com.google.common.truth.Truth
import com.example.flickraiexemple.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PhotosTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = Photos(
            1,
            998,
            100,
            9975,
            emptyList()
        )
        Truth.assertThat(model.page).isEqualTo(1)
        Truth.assertThat(model.pages).isEqualTo(998)
        Truth.assertThat(model.perpage).isEqualTo(100)
        Truth.assertThat(model.total).isEqualTo(9975)
        Truth.assertThat(model.photo).isEmpty()
    }
}
