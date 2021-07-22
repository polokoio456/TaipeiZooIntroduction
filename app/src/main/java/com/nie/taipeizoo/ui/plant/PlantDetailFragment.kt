package com.nie.taipeizoo.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.nie.taipeizoo.R
import com.nie.taipeizoo.base.BaseFragment
import com.nie.taipeizoo.data.remote.model.plant.Plant
import com.nie.taipeizoo.databinding.FragmentPlantDetailBinding

class PlantDetailFragment : BaseFragment() {

    companion object {
        private const val KEY_PLANT = "key_plant"

        fun newInstance(plant: Plant): PlantDetailFragment {
            return Bundle().apply {
                putSerializable(KEY_PLANT, plant)
            }.let { bundle ->
                PlantDetailFragment().apply {
                    arguments = bundle
                }
            }
        }
    }

    override val viewModel: Nothing? = null

    private lateinit var binding: FragmentPlantDetailBinding

    private val plant by lazy { requireArguments().getSerializable(KEY_PLANT) as Plant }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { exitPage() }

        initView()
    }

    private fun initView() {
        Glide.with(binding.root.context)
            .load(plant.picUrl)
            .into(binding.imagePlant)

        binding.toolbar.title = plant.chineseName
        binding.textName.text = "${plant.chineseName}\n${plant.englishName.trim()}"
        binding.textAlias.text = "${binding.root.context.getString(R.string.alias)}：\n${plant.alsoKnown.trim()}"
        binding.textIntroduction.text = "${binding.root.context.getString(R.string.introduction)}：\n${plant.brief.trim()}"
        binding.textIdentify.text = "${binding.root.context.getString(R.string.identify)}：\n${plant.feature.trim()}"
        binding.textFunction.text = "${binding.root.context.getString(R.string.function)}：\n${plant.function.trim()}"
        binding.textUpdatedTime.text = "${binding.root.context.getString(R.string.last_update_time)}${plant.lastUpdateTime.trim()}"
    }

    private fun handleBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitPage()
            }
        })
    }

    private fun exitPage() {
        parentFragment?.childFragmentManager?.popBackStack()
    }
}