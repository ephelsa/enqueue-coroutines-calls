package com.github.ephelsa.enqueuecoroutines

interface AlbumRepository {

    suspend fun getAlbums(): Result<List<Album>>

    suspend fun albumExists(albumId: Int): Result<Boolean>
}