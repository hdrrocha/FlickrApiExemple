package com.example.flickraiexemple.domain.usercase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.flickraiexemple.data.repository.HomeFactory
import com.example.flickraiexemple.helper.CoroutinesTestRule
import com.example.flickraiexemple.helper.PagingDataTest
import com.example.flickraiexemple.domain.mapper.abs.FlickerHomeMapper
import com.example.flickraiexemple.domain.repository.FlickerHomeRepository
import com.example.flickraiexemple.domain.usercase.abs.FlickerHomeUseCase
import com.google.common.truth.Truth.assertThat
import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.domain.uimodel.PhotoUi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlickerHomeUseCaseImpTest : PagingDataTest() {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var mockRepository: FlickerHomeRepository

    @MockK
    private lateinit var mockMapper: FlickerHomeMapper

    private lateinit var sut: FlickerHomeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = FlickerHomeUseCaseImp(
            flickerHomeRepository = mockRepository,
            mapper = mockMapper
        )
    }

    @Test
    fun fetchImagesSuccessTest() = runBlocking {
        val mapperInput = PagingData.from(HomeFactory.photoList)

        val expected = HomeFactory.photoListUI

        coEvery { mockRepository.fetchImages() }.returns(flowOf(mapperInput))
        every { mockMapper.map(any()) }.returns(PagingData.from(expected))

        sut.fetchImages().collect {
            val actual = getDifferSnapshot(it)

            assertThat(actual).isNotEmpty()
            assertThat(actual.items).isNotEmpty()
            assertThat(actual.items).isEqualTo(expected)
        }

        verify { mockMapper.map(mapperInput) }
    }

    @Test
    fun fetchImagesErrorTest() = runBlocking {
        val mapperInput = PagingData.empty<Photo>()
        val expected = PagingData.empty<PhotoUi>()

        coEvery { mockRepository.fetchImages() }.returns(flowOf(mapperInput))
        every { mockMapper.map(any()) }.returns(expected)

        sut.fetchImages().collect {
            val actual = getDifferSnapshot(it)

            assertThat(actual).isEmpty()
        }

        verify { mockMapper.map(mapperInput) }
    }
}
