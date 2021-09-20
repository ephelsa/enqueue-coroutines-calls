package com.github.ephelsa.enqueuecoroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AlbumRepositoryImpl(
    private val albumService: AlbumService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): AlbumRepository {

    override suspend fun getAlbums(): Result<List<Album>> = withContext(dispatcher) {
        try {
            val result = albumService.albums()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun albumExists(albumId: Int): Result<Boolean> = withContext(dispatcher) {
        val found = getAlbums().getOrNull()?.map { it.id }?.contains(albumId)

        if (found != null)
            Result.success(found)
        else
            Result.failure(NullPointerException("Not found!"))
    }
}