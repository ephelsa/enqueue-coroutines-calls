package com.github.ephelsa.enqueuecoroutines

import android.view.View

interface ViewInterpreter {
    val view: View
    val albumId: Int
    var state: Boolean
    var isLoading: Boolean
}