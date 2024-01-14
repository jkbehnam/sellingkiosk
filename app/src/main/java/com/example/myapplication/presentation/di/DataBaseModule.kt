package com.example.myapplication.presentation.di

import android.content.Context
import androidx.room.Room

import com.example.myapplication.data.FoodDao
import com.example.myapplication.data.FoodDatabase
import com.example.myapplication.domain.FoodRepository1
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context):FoodDatabase{

        return FoodDatabase.getInstance(context)
    }
    @Singleton
    @Provides
    fun provideMovieDao(foodDatabase: FoodDatabase):FoodDao{
        return foodDatabase.foodDao()
    }

}