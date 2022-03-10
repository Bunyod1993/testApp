package com.example.patient.dependencyInjection

import com.example.patient.repositories.auth.AuthRepository
import com.example.patient.repositories.auth.AuthRepositoryImpl
import com.example.patient.repositories.register.RegisterRepository
import com.example.patient.repositories.register.RegisterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideAuthRepository(repoImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideRegisterRepository(repoImpl: RegisterRepositoryImpl): RegisterRepository
}