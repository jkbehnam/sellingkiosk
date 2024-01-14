package com.example.myapplication.presentation.di

import com.example.myapplication.data.FoodDao
import com.example.myapplication.data.FoodRepositoryImpl
import com.example.myapplication.domain.FoodRepository1
import dagger.Module
import dagger.Provides

@Module
class FoodModule {
    @Provides
    fun provideFoodRepository(foodDao: FoodDao): FoodRepository1 {
        return FoodRepositoryImpl(foodDao)
    }
}