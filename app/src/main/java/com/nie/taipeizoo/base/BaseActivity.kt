package com.nie.taipeizoo.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.nie.taipeizoo.R

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel?

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setView(LayoutInflater.from(this).inflate(R.layout.dialog_progress, null))
            .create()

        observableLiveData()
    }

    private fun observableLiveData() {
        viewModel?.showLoading?.observe(this, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.dismiss()
    }
}