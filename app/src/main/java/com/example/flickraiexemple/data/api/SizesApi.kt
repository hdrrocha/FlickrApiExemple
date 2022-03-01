package com.example.flickraiexemple.data.api


import com.example.flickraiexemple.data.api.keys.FLICKR_API_KEY
import com.example.flickraiexemple.data.model.SizesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SizesApi {

    @GET("?method=flickr.photos.getSizes&api_key=${FLICKR_API_KEY}&format=json&nojsoncallback=1")
    suspend fun getImageSize(@Query("photo_id") idImage: String): SizesResponse

}
