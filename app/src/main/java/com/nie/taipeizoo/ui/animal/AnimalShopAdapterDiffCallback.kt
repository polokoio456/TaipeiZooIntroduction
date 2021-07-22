package com.nie.taipeizoo.ui.animal

import androidx.recyclerview.widget.DiffUtil
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShop

class AnimalShopAdapterDiffCallback(
    private val oldList: List<AnimalShop>,
    private val newList: List<AnimalShop>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}