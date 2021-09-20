package com.github.ephelsa.enqueuecoroutines

import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Interactor private constructor(
    private val viewInterpreter: ViewInterpreter,
    private val albumRepository: AlbumCacheInMemory,
) {
    companion object {
        @Volatile
        private var INSTANCE: Interactor? = null

        fun getInstance(viewInterpreter: ViewInterpreter, albumRepository: AlbumCacheInMemory): Interactor {
            return INSTANCE ?: synchronized(this) {
                Interactor(viewInterpreter, albumRepository)
            }
        }
    }

    private val scope: CoroutineScope?
        get() = viewInterpreter.view.findViewTreeLifecycleOwner()?.lifecycle?.coroutineScope

    private fun doAsyncWithHandler(
        task: suspend () -> Result<*>,
    ) {
        val handler = CoroutineExceptionHandler { _, _ ->
            Toast.makeText(viewInterpreter.view.context, "An error occurs", Toast.LENGTH_SHORT).show()
        }

        scope?.launch(handler) {
            viewInterpreter.isLoading = true
            task()
            viewInterpreter.isLoading = false
        }
    }

    //
    fun check() {
        doAsyncWithHandler {
            albumRepository.dataExits(viewInterpreter.albumId).map {
                viewInterpreter.state = it
            }
        }
    }
}