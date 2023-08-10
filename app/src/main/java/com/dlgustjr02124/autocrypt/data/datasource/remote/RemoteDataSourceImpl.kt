package com.dlgustjr02124.autocrypt.data.datasource.remote

import com.dlgustjr02124.autocrypt.data.api.AutoCryptService
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: AutoCryptService) :
    RemoteDataSource {

    /*override fun remoteGetCenterList() : Flow<PagingData<Data>>{
        return Pager(PagingConfig(pageSize = 10, maxSize = 100,enablePlaceholders = false)){
            AutoCryptPagingSource(service)
        }.flow
    }*/
    override fun remoteGetCenterList(page: Int): Flow<NetworkResponse> {
        return flow {
            emit(service.getCenterList(page = page, perPage = 10))
        }
    }

}