package com.example.flickraiexemple.domain.mapper

import com.example.flickraiexemple.data.model.*
import com.example.flickraiexemple.domain.mapper.abs.SizePhotosMapper
import com.example.flickraiexemple.domain.uimodel.*

class SizePhotosMapperImp : SizePhotosMapper {
    override fun map(input: SizesResponse): SizesResponseUi {
        return SizesResponseUi(
            mapSizes(input.sizes),
            input.stat
        )
    }


    private fun mapSizes(input: Sizes): SizesUi {
        return SizesUi(
            input.canblog,
            input.canprint,
            input.candownload,
            mapSizeList(input.size)
        )
    }

    private fun mapSizeList(input: List<Size>): MutableList<SizeUi?> {
        val outPut: MutableList<SizeUi?> = mutableListOf()
        input.forEach { size ->
            outPut.add(
                SizeUi(
                    size.label,
                    size.width,
                    size.height,
                    size.source,
                    size.url,
                    size.media
                )
            )
        }
        return outPut
    }

}
