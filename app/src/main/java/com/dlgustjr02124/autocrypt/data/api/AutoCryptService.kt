package com.dlgustjr02124.autocrypt.data.api

import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import com.dlgustjr02124.autocrypt.data.setting.Constant
import com.dlgustjr02124.autocrypt.data.setting.Constant.Companion.GET_CENTER_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Flow

interface AutoCryptService {
    @GET(GET_CENTER_LIST)
    suspend fun getCenterList(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("returnType") returnType: String = Constant.RETURN_TYPE,
        @Query("serviceKey") serviceKey: String = Constant.TOKEN
    ): NetworkResponse
}