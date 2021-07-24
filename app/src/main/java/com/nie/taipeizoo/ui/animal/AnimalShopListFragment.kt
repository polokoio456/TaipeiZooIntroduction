package com.nie.taipeizoo.ui.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.nie.taipeizoo.R
import com.nie.taipeizoo.base.BaseFragment
import com.nie.taipeizoo.databinding.FragmentAnimalShopListBinding
import com.nie.taipeizoo.extension.addFragment
import com.nie.taipeizoo.ui.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AnimalShopListFragment : BaseFragment() {

    companion object {
        fun newInstance() = AnimalShopListFragment()
    }

    private lateinit var binding: FragmentAnimalShopListBinding

    override val viewModel by sharedViewModel<MainViewModel>()

    private val adapter by inject<AnimalShopAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimalShopListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAnimalShop()

        initView()
        setOnClickListener()
        observableLiveData()
    }

    override fun onDestroy() {
        adapter.clear()
        super.onDestroy()
    }

    private fun initView() {
        binding.animalShopRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.animalShopRecyclerView.adapter = adapter
    }

    private fun setOnClickListener() {
        adapter.onItemClicked = {
            childFragmentManager.addFragment(R.id.container, AnimalShopDetailFragment.newInstance(it))
        }
    }

    private fun observableLiveData() {
        viewModel.animalShopList.observe(viewLifecycleOwner, {
            adapter.addAll(it)
        })

        viewModel.showServerError.observe(viewLifecycleOwner, {
            showServerErrorDialog()
        })
    }

    private fun showServerErrorDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setCancelable(false)
            setMessage(requireContext().getString(R.string.server_error))
            setNegativeButton(requireContext().getString(R.string.retry), { _, _ ->
                viewModel.fetchAnimalShop()
            })
            setPositiveButton(requireContext().getString(R.string.cancel), { _, _ ->
                requireActivity().finish()
            })
        }.create().show()
    }

    private fun handleBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }
}