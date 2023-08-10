package com.dlgustjr02124.autocrypt.data.datasource.remote

import androidx.paging.PagingData
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    //fun remoteGetCenterList(): Flow<PagingData<Data>>
    fun remoteGetCenterList(page: Int): Flow<NetworkResponse>
}