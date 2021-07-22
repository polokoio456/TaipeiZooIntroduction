package com.nie.taipeizoo.ui.plant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.nie.taipeizoo.R
import com.nie.taipeizoo.data.remote.model.plant.Plant
import com.nie.taipeizoo.databinding.ItemPlantBinding
import com.nie.taipeizoo.extension.throttleClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    private val items = mutableListOf<Plant>()

    private val compositeDisposable = CompositeDisposable()

    var onItemClicked = { _: Plant -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(items[position], compositeDisposable, onItemClicked)
    }

    override fun getItemCount() = items.size

    fun addAll(list: List<Plant>) {
        DiffUtil.calculateDiff(PlantAdapterDiffCallback(items, list)).let {
            items.clear()
            items.addAll(list)
            it.dispatchUpdatesTo(this)
        }
    }

    fun clear() = compositeDisposable.clear()

    class PlantViewHolder(private val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant, compositeDisposable: CompositeDisposable, onItemClicked: (Plant) -> Unit) {
            RxView.clicks(binding.root)
                .throttleClick()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onItemClicked.invoke(item)
                }.addTo(compositeDisposable)

            val shapeAppearanceModel = binding.imageAnimalShop.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(binding.root.context.resources.getDimension(R.dimen.image_corner))
                .build()

            binding.imageAnimalShop.shapeAppearanceModel = shapeAppearanceModel

            Glide.with(binding.root.context)
                .load(item.picUrl)
                .placeholder(R.drawable.place_holder_grey)
                .into(binding.imageAnimalShop)

            binding.textTitle.text = item.chineseName
            binding.textDescription.text = item.alsoKnown
        }
    }
}