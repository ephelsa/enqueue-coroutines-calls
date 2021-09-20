package com.github.ephelsa.enqueuecoroutines

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album(
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String
)
