package com.github.ephelsa.enqueuecoroutines

import android.util.Log
import java.util.Calendar
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AlbumCacheInMemory private constructor(
    private val network: AlbumRepository,
) : CacheRepository<List<Album>, Int> {

    companion object {
        @Volatile
        private var INSTANCE: AlbumCacheInMemory? = null

        fun getInstance(network: AlbumRepository): AlbumCacheInMemory {
            return INSTANCE ?: synchronized(this) {
                val instance = AlbumCacheInMemory(network)
                INSTANCE = instance
                instance
            }
        }
    }

    override val shouldBeUpdated: Boolean
        get() {
            Log.v("AlbumCache", "${inMemory.lastUpdate}")

            return Calendar.getInstance().timeInMillis > inMemory.lastUpdate + 15_000
        }

    private val mutex = Mutex()

    private val inMemory = Memo(
        Result.success(emptyList()),
        0L
    )

    override suspend fun fetchData(): Result<List<Album>> = mutex.withLock {
        if (shouldBeUpdated) {
            inMemory.items = network.getAlbums()
            inMemory.lastUpdate = Calendar.getInstance().timeInMillis
        }

        return inMemory.items
    }

    override suspend fun dataExits(id: Int): Result<Boolean> {
        return fetchData().map {
            it.map(Album::id).contains(id)
        }
    }

    data class Memo(
        var items: Result<List<Album>>,
        var lastUpdate: Long,
    )
}