package com.github.ephelsa.enqueuecoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ephelsa.enqueuecoroutines.databinding.ItemViewBinding

class Adapter(
    private val ids: List<Int>,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val id = ids[position]

        holder.customView.album = id
        holder.customView.text = id.toString()
    }

    override fun getItemCount(): Int = ids.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindings: ItemViewBinding = ItemViewBinding.bind(itemView)
        val customView = bindings.customView
    }
}