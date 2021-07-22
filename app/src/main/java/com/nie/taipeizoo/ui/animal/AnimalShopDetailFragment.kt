package com.nie.taipeizoo.ui.animal

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.nie.taipeizoo.R
import com.nie.taipeizoo.base.BaseFragment
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShop
import com.nie.taipeizoo.databinding.FragmentAnimalShopDetailBinding
import com.nie.taipeizoo.extension.addFragment
import com.nie.taipeizoo.extension.hide
import com.nie.taipeizoo.extension.show
import com.nie.taipeizoo.extension.throttleClick
import com.nie.taipeizoo.ui.plant.PlantAdapter
import com.nie.taipeizoo.ui.plant.PlantDetailFragment
import com.nie.taipeizoo.ui.plant.PlantViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalShopDetailFragment : BaseFragment() {

    companion object {
        private const val KEY_ANIMAL_SHOP = "key_animal_shop"

        fun newInstance(shop: AnimalShop): AnimalShopDetailFragment {
            return Bundle().apply {
                putSerializable(KEY_ANIMAL_SHOP, shop)
            }.let { bundle ->
                AnimalShopDetailFragment().apply {
                    arguments = bundle
                }
            }
        }
    }

    override val viewModel by viewModel<PlantViewModel>()

    private val adapter by inject<PlantAdapter>()

    private lateinit var binding: FragmentAnimalShopDetailBinding

    private val animalShop by lazy { requireArguments().getSerializable(KEY_ANIMAL_SHOP) as AnimalShop }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimalShopDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPlantList(animalShop.name)

        initView()
        setOnClickListener()
        observableLiveData()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    private fun initView() {
        binding.textTitle.text = animalShop.name

        binding.recyclerViewPlant.isNestedScrollingEnabled = false
        binding.recyclerViewPlant.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewPlant.adapter = adapter

        binding.textDescription.text = animalShop.info
        binding.textBusinessHours.text = animalShop.memo
        binding.textPosition.text = animalShop.category

        Glide.with(binding.root.context)
            .load(animalShop.picUrl)
            .placeholder(R.drawable.place_holder_grey)
            .into(binding.imageAnimalShop)
    }

    private fun setOnClickListener() {
        RxView.clicks(binding.imageBack)
            .throttleClick()
            .subscribe {
                exitPage()
            }.addTo(compositeDisposable)

        RxView.clicks(binding.openBrowser)
            .throttleClick()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                openBrowser(animalShop.url)
            }.addTo(compositeDisposable)

        adapter.onItemClicked = {
            childFragmentManager.addFragment(R.id.container, PlantDetailFragment.newInstance(it))
        }
    }

    private fun observableLiveData() {
        viewModel.plantList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.addAll(it)
            } else {
                binding.recyclerViewPlant.hide()
                binding.textNoData.show()
            }
        })

        viewModel.showServerError.observe(viewLifecycleOwner, {
            showServerErrorDialog()
        })
    }

    private fun showServerErrorDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setCancelable(false)
            setMessage("伺服器錯誤")
            setNegativeButton("重試", { _, _ ->
                viewModel.fetchPlantList(animalShop.name)
            })
            setPositiveButton("取消", { _, _ ->
                requireActivity().finish()
            })
        }.create().show()
    }

    private fun openBrowser(url: String) {
        val colorInt = Color.parseColor("#000000")
        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(colorInt)
            .build()

        val builder = CustomTabsIntent.Builder()
        builder.setDefaultColorSchemeParams(defaultColors)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
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