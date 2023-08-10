package com.dlgustjr02124.autocrypt.domain.repository

import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface AutoCryptRepository {
    //fun getCenterList() : Flow<PagingData<Data>>
    fun getCenterList(page: Int): Flow<NetworkResponse>
}