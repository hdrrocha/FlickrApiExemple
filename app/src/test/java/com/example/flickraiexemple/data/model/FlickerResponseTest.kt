package com.example.flickraiexemple.data.model

import com.google.common.truth.Truth
import com.mindera.flickergallery.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlickerResponseTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = FlickerResponse(
            Photos(
                1,
                998,
                100,
                9975,
                emptyList()
            ),
            "ok"
        )
        Truth.assertThat(model.photos.page).isEqualTo(1)
        Truth.assertThat(model.photos.pages).isEqualTo(998)
        Truth.assertThat(model.photos.perpage).isEqualTo(100)
        Truth.assertThat(model.photos.total).isEqualTo(9975)
        Truth.assertThat(model.photos.photo).isEmpty()
        Truth.assertThat(model.stat).isEqualTo("ok")
    }
}
