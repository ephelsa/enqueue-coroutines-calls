package com.github.ephelsa.enqueuecoroutines

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox

class CustomView(
    context: Context,
    attrs: AttributeSet? = null,
) : AppCompatCheckBox(context, attrs), ViewInterpreter {

    private val albumRepository: AlbumRepository = RetrofitStuff.albumRepository()
    private val albumCacheRepository: AlbumCacheInMemory = AlbumCacheInMemory.getInstance(albumRepository)
    private val interactor = Interactor.getInstance(this, albumCacheRepository)

    var album: Int? = null

    override val view: View
        get() = this

    override val albumId: Int
        get() = album ?: throw NullPointerException("${CustomView::album.name} is null")

    override var state: Boolean
        get() = isChecked
        set(value) {
            isChecked = value
        }
    override var isLoading: Boolean = false
        set(value) {
            field = value

            if (value)
                setBackgroundColor(Color.RED)
            else
                setBackgroundColor(Color.TRANSPARENT)
        }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        album.let { interactor.check() }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + album.hashCode()

        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as CustomView

        if (album != other.album) return false

        return true
    }
}