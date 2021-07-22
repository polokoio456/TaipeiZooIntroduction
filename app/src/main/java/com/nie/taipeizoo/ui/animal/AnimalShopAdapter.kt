package com.nie.taipeizoo.ui.animal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.nie.taipeizoo.R
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShop
import com.nie.taipeizoo.databinding.ItemAnimalShopBinding
import com.nie.taipeizoo.extension.throttleClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class AnimalShopAdapter : RecyclerView.Adapter<AnimalShopAdapter.ZooShopViewHolder>() {

    private val items = mutableListOf<AnimalShop>()

    private val compositeDisposable = CompositeDisposable()

    var onItemClicked = { _: AnimalShop ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooShopViewHolder {
        val binding = ItemAnimalShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZooShopViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ZooShopViewHolder, position: Int) {
        holder.bind(items[position], compositeDisposable, onItemClicked)
    }

    fun addAll(list: List<AnimalShop>) {
        DiffUtil.calculateDiff(AnimalShopAdapterDiffCallback(items, list)).let {
            items.clear()
            items.addAll(list)
            it.dispatchUpdatesTo(this)
        }
    }

    fun clear() = compositeDisposable.clear()

    class ZooShopViewHolder(private val binding: ItemAnimalShopBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimalShop, compositeDisposable: CompositeDisposable, onItemClicked: (AnimalShop) -> Unit) {
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

            binding.textTitle.text = item.name
            binding.textDescription.text = item.info
            binding.textBusinessHours.text = item.memo
        }
    }
}