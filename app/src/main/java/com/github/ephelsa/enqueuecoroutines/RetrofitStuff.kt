package com.github.ephelsa.enqueuecoroutines

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitStuff {

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun albumService(): AlbumService {
        return retrofitInstance().create(AlbumService::class.java)
    }

    fun albumRepository(): AlbumRepository {
        return AlbumRepositoryImpl(albumService())
    }
}