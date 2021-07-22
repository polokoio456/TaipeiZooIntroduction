package com.nie.taipeizoo.ui.plant

import androidx.recyclerview.widget.DiffUtil
import com.nie.taipeizoo.data.remote.model.plant.Plant

class PlantAdapterDiffCallback(
    private val oldList: List<Plant>,
    private val newList: List<Plant>
) : DiffUtil.Callback()  {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}