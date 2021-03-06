package com.nie.taipeizoo.ui

import android.os.Bundle
import com.nie.taipeizoo.R
import com.nie.taipeizoo.base.BaseActivity
import com.nie.taipeizoo.base.BaseViewModel
import com.nie.taipeizoo.databinding.ActivityMainBinding
import com.nie.taipeizoo.extension.addFragment
import com.nie.taipeizoo.ui.animal.AnimalShopListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override val viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.addFragment(R.id.container, AnimalShopListFragment.newInstance(), isSlide = false)
    }
}