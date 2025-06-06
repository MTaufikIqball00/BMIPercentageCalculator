package com.example.bmipercentagecalculator.di

import com.example.bmipercentagecalculator.repository.BmiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBmiCalculatorRepository(): BmiRepository {
        return BmiRepository()
    }

}