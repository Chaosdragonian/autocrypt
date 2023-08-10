package com.dlgustjr02124.autocrypt.presentation.model

import android.os.Parcelable
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CenterDataModel(
    var address: NetworkResponse.Data
) : Parcelable
