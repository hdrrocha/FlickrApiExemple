package com.example.flickraiexemple.data.model

import com.google.common.truth.Truth
import com.mindera.flickergallery.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PhotoTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = Photo(
            "51539104646",
            "194007867@N07",
            "5f0e218861",
            65535,
            66,
            "Buffalo Dogs Animals from Travels",
            1,
            0,
            0
        )
        Truth.assertThat(model.id).isEqualTo("51539104646")
        Truth.assertThat(model.owner).isEqualTo("194007867@N07")
        Truth.assertThat(model.secret).isEqualTo("5f0e218861")
        Truth.assertThat(model.server).isEqualTo(65535)
        Truth.assertThat(model.farm).isEqualTo(66)
        Truth.assertThat(model.title).isEqualTo("Buffalo Dogs Animals from Travels")
        Truth.assertThat(model.ispublic).isEqualTo(1)
        Truth.assertThat(model.isfriend).isEqualTo(0)
        Truth.assertThat(model.isfamily).isEqualTo(0)
    }
}
