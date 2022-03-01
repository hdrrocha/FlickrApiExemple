package com.example.flickraiexemple.data.repository

import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.domain.uimodel.PhotoUi

object HomeFactory {

    val photoList get() = listOf(photoItem, photoItem, photoItem, photoItem, photoItem)

    val photoItem
        get() = Photo(
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

    val photoListUI get() = listOf(photoItemUI, photoItemUI, photoItemUI, photoItemUI, photoItemUI)

    val photoItemUI
        get() = PhotoUi(
            "51539104646",
            "194007867@N07",
            "5f0e218861"
        )
}
