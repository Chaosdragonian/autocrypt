package com.dlgustjr02124.autocrypt.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dlgustjr02124.autocrypt.presentation.base.BaseViewModel
import com.naver.maps.map.NaverMap

class MapViewModel : BaseViewModel() {
    private val _naverMapObejctLivedata = MutableLiveData<NaverMap>()


    val naverMapObjectLivedata = _naverMapObejctLivedata


    fun setNaverMapObject(naverMap: NaverMap) {
        _naverMapObejctLivedata.postValue(naverMap)
    }
}