package com.dlgustjr02124.autocrypt.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import com.dlgustjr02124.autocrypt.domain.usecase.GetCenterDataListUseCase
import com.dlgustjr02124.autocrypt.presentation.base.BaseViewModel
import com.dlgustjr02124.autocrypt.presentation.view.fragment.SplashFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getCenterDataListUseCase: GetCenterDataListUseCase) :
    BaseViewModel() {
    private val _progressValueLiveData = MutableLiveData(0)
    private val _progressBarVisibilityLiveData = MutableLiveData(View.GONE)
    private val _centerListLiveData =
        MutableLiveData<ArrayList<NetworkResponse.Data>>(arrayListOf())
    private val _loadCompleteLiveData = MutableLiveData(false)
    private val _nextCounterLiveData = MutableLiveData(0)
    val timer: Timer = Timer()


    val progressValueLiveData: LiveData<Int> = _progressValueLiveData
    val progressBarVisibilityLiveData: LiveData<Int> = _progressBarVisibilityLiveData
    val centerListLiveData: LiveData<ArrayList<NetworkResponse.Data>> = _centerListLiveData
    val loadCompleteLiveData: LiveData<Boolean> = _loadCompleteLiveData
    val nextCounterLiveData: LiveData<Int> = _nextCounterLiveData

    fun getCenterList(page: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getCenterDataListUseCase.invoke(page)
                .onStart { _progressBarVisibilityLiveData.postValue(View.VISIBLE) }
                .onCompletion { }
                .catch { _toastLiveData.postValue("error happened") }
                .collect {
                    withContext(Dispatchers.Main) {
                        _nextCounterLiveData.value.apply {
                            if ((this ?: 0) < SplashFragment.CENTER_PAGE_SIZE) {
                                _nextCounterLiveData.value = this?.plus(1)
                            }
                        }
                        _centerListLiveData.postValue(_centerListLiveData.value?.apply {
                            if (this.size < SplashFragment.CENTER_LIST_SIZE) {
                                addAll(it.data)
                            }
                        })
                    }
                }
        }
    }

    fun startProgressTimer() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                _progressValueLiveData.value?.let {
                    if (it < 80) {
                        _progressValueLiveData.postValue(it + 5)
                    } else if (it in 80..99 && _loadCompleteLiveData.value == true) {
                        _progressValueLiveData.postValue(it + 5)
                    } else if (it == 100) {
                        timer.cancel()
                        _progressBarVisibilityLiveData.postValue(View.GONE)
                    }
                }
            }
        }, 0, 100)

    }

    /*fun setProgressValue(value : Int){
        _progressValueLiveData.value = value
    }*/

    fun setLoadComplete() {

        _loadCompleteLiveData.value = true

    }
}