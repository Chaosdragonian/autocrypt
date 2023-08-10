package com.dlgustjr02124.autocrypt.domain.usecase

import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import com.dlgustjr02124.autocrypt.domain.repository.AutoCryptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCenterDataListUseCaseImpl @Inject constructor(private val repository: AutoCryptRepository) :
    GetCenterDataListUseCase {
    //override fun invoke() = repository.getCenterList()
    override fun invoke(page: Int): Flow<NetworkResponse> = repository.getCenterList(page)
}