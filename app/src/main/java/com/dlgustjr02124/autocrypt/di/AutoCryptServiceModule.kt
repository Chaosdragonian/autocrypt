package com.dlgustjr02124.autocrypt.di

import com.dlgustjr02124.autocrypt.data.api.AutoCryptService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AutoCryptServiceModule {
    @Provides
    @Singleton
    fun providesAutoCryptAPI(retrofit: Retrofit): AutoCryptService {
        return retrofit.create(AutoCryptService::class.java)
    }
}