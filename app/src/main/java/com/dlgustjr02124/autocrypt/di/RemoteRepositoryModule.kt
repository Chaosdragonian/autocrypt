package com.dlgustjr02124.autocrypt.di

import com.dlgustjr02124.autocrypt.data.api.AutoCryptService
import com.dlgustjr02124.autocrypt.data.datasource.remote.RemoteDataSource
import com.dlgustjr02124.autocrypt.data.datasource.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteRepositoryModule {
    @Provides
    fun providesRemoteRepositoryModule(service: AutoCryptService): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }
}