package com.example.flickraiexemple.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.flickraiexemple.helper.CoroutinesTestRule
import com.example.flickraiexemple.helper.PagingDataTest
import com.example.flickraiexemple.domain.repository.FlickerHomeRepository
import com.google.common.truth.Truth.assertThat
import com.example.flickraiexemple.data.model.Photo
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FlickerHomeRepositoryImpTest : PagingDataTest() {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var mockPager: Pager<Int, Photo>

    private lateinit var sut: FlickerHomeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = FlickerHomeRepositoryImp(pager = mockPager)
    }

    @Test
    fun fetchImagesSuccessTest() = runBlocking {
        val expected = HomeFactory.photoList
        every { mockPager.flow }.returns(flowOf(PagingData.from(expected)))

        sut.fetchImages().collect {
            val actual = getDifferSnapshot(it)

            assertThat(actual).isNotEmpty()
            assertThat(actual.items).isNotEmpty()
            assertThat(actual.items).isEqualTo(expected)
        }
    }

    @Test
    fun fetchImagesErrorTest() = runBlocking {
        every { mockPager.flow }.returns(flowOf(PagingData.empty()))

        val data = sut.fetchImages().singleOrNull()
        val actual = getDifferSnapshot(data!!)

        assertThat(actual).isEmpty()
    }
}
