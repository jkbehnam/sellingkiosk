package com.example.myapplication.presentation.di

import com.example.myapplication.data.FoodDao
import com.example.myapplication.data.FoodRepositoryImpl
import com.example.myapplication.domain.FooodRepositpry
import dagger.Module
import dagger.Provides


@Module
object RepositoryModule {
    @Provides
    fun provideItemRepository(itemDao: FoodDao): FooodRepositpry {
        return FoodRepositoryImpl(itemDao)
    }
}