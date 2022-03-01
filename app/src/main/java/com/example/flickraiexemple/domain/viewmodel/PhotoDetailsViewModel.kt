package com.example.flickraiexemple.domain.viewmodel

import androidx.lifecycle.*
import com.example.flickraiexemple.domain.uimodel.SizeUi
import com.example.flickraiexemple.domain.usercase.abs.SizesPhotosUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class PhotoDetailsViewModel(
    private val useCase: SizesPhotosUseCase
) : ViewModel() {

    private val mutableData = MutableLiveData<SizeUi?>()

    val data get() = mutableData as LiveData<SizeUi?>

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadData(id: String) = viewModelScope.launch {

        try {
            loading.value = true
            useCase.getImageSize(id).sizes.size.forEach { photos ->
                photos.takeIf {
                    it?.label == "Large"
                }?.let {
                    mutableData.value = it
                }
            }

        } catch (e: Exception) {
            error.value = true
            loading.value = false
        } finally {
            loading.value = false
        }
    }
}
