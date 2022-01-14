package com.example.consultantalif.dependencyInjection

import com.example.consultantalif.repositories.auth.AuthRepository
import com.example.consultantalif.repositories.auth.AuthRepositoryImpl
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