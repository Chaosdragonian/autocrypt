package com.dlgustjr02124.autocrypt.di

import com.dlgustjr02124.autocrypt.domain.repository.AutoCryptRepository
import com.dlgustjr02124.autocrypt.domain.usecase.GetCenterDataListUseCase
import com.dlgustjr02124.autocrypt.domain.usecase.GetCenterDataListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object GetCenterDataListUseCaseModule {
    @ViewModelScoped
    @Provides
    fun providesCenterDataListUseCaseModule(repository: AutoCryptRepository): GetCenterDataListUseCase {
        return GetCenterDataListUseCaseImpl(repository)
    }
}