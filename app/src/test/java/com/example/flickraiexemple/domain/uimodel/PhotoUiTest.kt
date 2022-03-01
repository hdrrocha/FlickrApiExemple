package com.example.flickraiexemple.domain.uimodel

import com.google.common.truth.Truth
import com.example.flickraiexemple.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PhotoUiTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = PhotoUi(
            "51539104646",
            "www",
            "Buffalo Dogs Animals from Travels"
        )
        Truth.assertThat(model.id).isEqualTo("51539104646")
        Truth.assertThat(model.url).isEqualTo("www")
        Truth.assertThat(model.title).isEqualTo("Buffalo Dogs Animals from Travels")

    }
}
