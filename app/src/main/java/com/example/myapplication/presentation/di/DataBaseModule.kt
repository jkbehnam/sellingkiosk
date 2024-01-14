package com.example.myapplication.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.FoodDao
import com.example.myapplication.data.FoodDatabase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
public class DataBaseModule {
    @Singleton
    @Provides
    fun provideMovieDataBase(context: Context):FoodDatabase{
     return Room.databaseBuilder(context,FoodDatabase::class.java,"food_database")
         .build()
    }
    @Singleton
    @Provides
    fun provideFoodDao(foodDatabase: FoodDatabase): FoodDao {
        return foodDatabase.foodDao()
    }






}