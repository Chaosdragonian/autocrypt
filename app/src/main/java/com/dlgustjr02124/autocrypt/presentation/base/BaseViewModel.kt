package com.dlgustjr02124.autocrypt.presentation.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val _toastLiveData = MutableLiveData<String>()
    protected val _snackbarLiveData = MutableLiveData<String>()
    protected val _progressLiveData = MutableLiveData(View.GONE)

    val toastLiveData: LiveData<String> = _toastLiveData
    val snackbarLiveData: LiveData<String> = _snackbarLiveData
    val progressLiveData: LiveData<Int> = _progressLiveData

    open fun setToast(message: String) {
        _toastLiveData.value = message
    }

    open fun setSnackbar(message: String) {
        _snackbarLiveData.value = message
    }
}