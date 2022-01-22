package com.example.patient.dependencyInjection

import com.example.patient.repositories.auth.AuthRepository
import com.example.patient.repositories.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideAuthRepository(repoImpl: AuthRepositoryImpl): AuthRepository
}