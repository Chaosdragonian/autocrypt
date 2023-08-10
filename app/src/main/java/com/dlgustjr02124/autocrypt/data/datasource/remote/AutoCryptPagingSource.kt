package com.dlgustjr02124.autocrypt.data.datasource.remote

import android.net.Network
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dlgustjr02124.autocrypt.data.api.AutoCryptService
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse

class AutoCryptPagingSource(private val autoCryptService: AutoCryptService) :
    PagingSource<Int, NetworkResponse.Data>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkResponse.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkResponse.Data> {
        return try {
            val page = params.key ?: 1
            val size = params.loadSize
            val response = autoCryptService.getCenterList(page = page, perPage = size)
            val result = response.data

            val prevKey = if (page == 1) null else page - 1
            val nextKey =
                if (result.isEmpty() || response.totalCount == response.currentCount) null else page + 1
            LoadResult.Page(
                data = result,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}