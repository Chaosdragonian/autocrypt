package com.dlgustjr02124.autocrypt.domain.usecase

import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface GetCenterDataListUseCase {
    operator fun invoke(page: Int): Flow<NetworkResponse>
}