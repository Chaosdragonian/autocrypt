package com.dlgustjr02124.autocrypt.di

import com.dlgustjr02124.autocrypt.data.datasource.remote.RemoteDataSource
import com.dlgustjr02124.autocrypt.data.repository.AutoCryptRepositoryImpl
import com.dlgustjr02124.autocrypt.domain.repository.AutoCryptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AutoCryptRepositoryModule {
    @Provides
    @Singleton
    fun providesAutoCryptRepositoryModule(remoteDataSource: RemoteDataSource): AutoCryptRepository {
        return AutoCryptRepositoryImpl(remoteDataSource)
    }
}