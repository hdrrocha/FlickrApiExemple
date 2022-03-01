package com.example.flickraiexemple.domain.uimodel

import com.google.common.truth.Truth
import com.example.flickraiexemple.BaseUnitTest
import kotlinx.coroutines.runBlocking

import org.junit.Test

class PhotosUiTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = PhotosUi(
            1,
            998,
            100,
            9975,
            mutableListOf()
        )
        Truth.assertThat(model.page).isEqualTo(1)
        Truth.assertThat(model.pages).isEqualTo(998)
        Truth.assertThat(model.perpage).isEqualTo(100)
        Truth.assertThat(model.total).isEqualTo(9975)
        Truth.assertThat(model.photo).isEmpty()
    }
}
