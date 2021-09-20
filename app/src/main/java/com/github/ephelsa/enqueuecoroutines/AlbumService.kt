package com.github.ephelsa.enqueuecoroutines

import retrofit2.http.GET

interface AlbumService {

    @GET("albums")
    suspend fun albums(): List<Album>
}