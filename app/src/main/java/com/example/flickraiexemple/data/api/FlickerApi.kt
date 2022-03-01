package com.example.flickraiexemple.data.api

import com.example.flickraiexemple.data.model.FlickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApi {

    @GET("?method=flickr.photos.search&api_key=${keys.FLICKR_API_KEY}&tags=dogs&format=json&nojsoncallback=1")
    suspend fun fetchImages(@Query("page") page: Int, @Query("perpage") size: Int): FlickerResponse

}
