package com.dlgustjr02124.autocrypt.data.repository

import com.dlgustjr02124.autocrypt.data.datasource.remote.RemoteDataSource
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import com.dlgustjr02124.autocrypt.domain.repository.AutoCryptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AutoCryptRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    AutoCryptRepository {
    //override fun getCenterList(): Flow<PagingData<Data>> = remoteDataSource.remoteGetCenterList()
    override fun getCenterList(page: Int): Flow<NetworkResponse> =
        remoteDataSource.remoteGetCenterList(page)
}