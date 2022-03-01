package com.example.flickraiexemple.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.flickraiexemple.data.repository.HomeFactory
import com.google.common.truth.Truth.assertThat
import com.example.flickraiexemple.helper.CoroutinesTestRule
import com.example.flickraiexemple.helper.PagingDataTest
import com.example.flickraiexemple.domain.usercase.abs.FlickerHomeUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FlickerHomeViewModelTest : PagingDataTest() {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var mockUseCase: FlickerHomeUseCase

    private lateinit var sut: FlickerHomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = FlickerHomeViewModel(useCase = mockUseCase).apply {
            data.observeForever { }
        }
    }

    @Test
    fun loadDataSuccessTest() = runBlocking {
        assertThat(sut.data.value).isNull()

        val expected = HomeFactory.photoListUI
        coEvery { mockUseCase.fetchImages() }.returns(flowOf(PagingData.from(expected)))

        sut.loadData()

        assertThat(sut.data.value).isNotNull()

        val actual = getDifferSnapshot(sut.data.value!!)

        assertThat(actual).isNotEmpty()
        assertThat(actual.items).isNotEmpty()
        assertThat(actual.items).isEqualTo(expected)
    }

    @Test
    fun loadDataFailureTest() = runBlocking {
        assertThat(sut.data.value).isNull()

        coEvery { mockUseCase.fetchImages() }.returns(flowOf(PagingData.empty()))

        sut.loadData()

        assertThat(sut.data.value).isNotNull()

        val actual = getDifferSnapshot(sut.data.value!!)
        assertThat(actual).isEmpty()
    }
}
