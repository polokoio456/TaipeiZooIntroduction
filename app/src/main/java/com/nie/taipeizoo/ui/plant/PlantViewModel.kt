package com.nie.taipeizoo.ui.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nie.taipeizoo.base.BaseViewModel
import com.nie.taipeizoo.data.remote.model.plant.Plant
import com.nie.taipeizoo.extension.bind
import com.nie.taipeizoo.repository.main.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class PlantViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList: LiveData<List<Plant>> = _plantList

    private val _showServerError = MutableLiveData<Boolean>()
    val showServerError: LiveData<Boolean> = _showServerError

    fun fetchPlantList(keyword: String) {
        mainRepository.fetchPlantList(keyword)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({
                _plantList.value = it.result.results
            }, {
                _showServerError.value = true
            }).bind(this)
    }
}