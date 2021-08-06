package com.nie.taipeizoo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nie.taipeizoo.base.BaseViewModel
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShop
import com.nie.taipeizoo.repository.main.MainRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

class AnimalViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    companion object {
        private const val LOG_TAG = "TaipeiZoo"
    }

    private val _animalShopList = MutableLiveData<List<AnimalShop>>()
    val animalShopList: LiveData<List<AnimalShop>> = _animalShopList

    private val _showServerError = MutableLiveData<Boolean>()
    val showServerError: LiveData<Boolean> = _showServerError

    fun fetchAnimalShop() {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            try {
                val response = mainRepository.fetchAnimalShop()
                _animalShopList.postValue(response.result.results)
            } catch (e: Exception) {
                Log.e(LOG_TAG, e.message ?: "fetchShop Failed!!!")
                _showServerError.postValue(true)
                hideLoading()
            }

            hideLoading()
        }
    }
}