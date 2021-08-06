package com.nie.taipeizoo.ui.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nie.taipeizoo.base.BaseViewModel
import com.nie.taipeizoo.data.remote.model.plant.Plant
import com.nie.taipeizoo.extension.bind
import com.nie.taipeizoo.repository.main.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PlantViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList: LiveData<List<Plant>> = _plantList

    private val _showServerError = MutableLiveData<Boolean>()
    val showServerError: LiveData<Boolean> = _showServerError

    fun fetchPlantList(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()

            try {
                val response = mainRepository.fetchPlantList(keyword)
                _plantList.postValue(response.result.results)
            } catch (e: Exception) {
                _showServerError.postValue(true)
                hideLoading()
            }

            hideLoading()
        }
    }
}