package com.nie.taipeizoo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nie.taipeizoo.base.BaseViewModel
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShop
import com.nie.taipeizoo.extension.bind
import com.nie.taipeizoo.repository.main.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    companion object {
        private const val LOG_TAG = "TaipeiZoo"
    }

    private val _animalShopList = MutableLiveData<List<AnimalShop>>()
    val animalShopList: LiveData<List<AnimalShop>> = _animalShopList

    private val _showServerError = MutableLiveData<Boolean>()
    val showServerError: LiveData<Boolean> = _showServerError

    fun fetchAnimalShop() {
        mainRepository.fetchAnimalShop()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                _animalShopList.value = it.result.results
            }, {
                Log.e(LOG_TAG, it.message ?: "fetchShop Failed!!!")
                _showServerError.value = true
            }).bind(this)
    }
}