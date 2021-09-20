package com.github.ephelsa.enqueuecoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ephelsa.enqueuecoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        configureRecycler()
    }

    private fun configureRecycler() {
        bindings.root.layoutManager = GridLayoutManager(this, 4)
        bindings.root.setHasFixedSize(false)
        bindings.root.adapter = adapter()
    }

    private fun adapter(): Adapter {
        val items = listOf(
            1, 2, -3, 4, 5, 6, -7, 8, -9, -10,
            -11, -12, -13, -14, -15, -16, -17, -18, -19, -20,
            21, 22, 23, 24, 25, -26, -27, -28, 29, -30,
            1, 2, -3, 4, 5, 6, -7, 8, -9, -10,
            -11, -12, -13, -14, -15, -16, -17, -18, -19, -20,
            21, 22, 23, 24, 25, -26, -27, -28, 29, -30,
        )

        return Adapter(items)
    }
}