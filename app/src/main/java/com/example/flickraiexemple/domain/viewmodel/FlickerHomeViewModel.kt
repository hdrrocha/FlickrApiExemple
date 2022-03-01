package com.example.flickraiexemple.domain.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flickraiexemple.domain.uimodel.PhotoUi
import com.example.flickraiexemple.domain.usercase.abs.FlickerHomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class FlickerHomeViewModel(
    private val useCase: FlickerHomeUseCase
) : ViewModel() {

    private val mutableData = MutableLiveData<PagingData<PhotoUi>>()

    val data get() = mutableData as LiveData<PagingData<PhotoUi>>

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadData() = viewModelScope.launch {
        try {
            loading.value = true
            if (mutableData.value != null) return@launch
            useCase.fetchImages().cachedIn(viewModelScope).collect { mutableData.value = it }

        } catch (e: Exception) {
            error.value = true
            loading.value = false
        } finally {
            loading.value = false
        }
    }
}
