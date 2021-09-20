package com.github.ephelsa.enqueuecoroutines

interface CacheRepository<Data, ID> {

    val shouldBeUpdated: Boolean

    suspend fun fetchData(): Result<Data>

    suspend fun dataExits(id: ID): Result<Boolean>
}