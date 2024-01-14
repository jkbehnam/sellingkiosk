package com.example.myapplication.presentation.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.myapplication.data.FoodDao
import com.example.myapplication.data.FoodDatabase
import com.example.myapplication.data.FoodRepositoryImpl
import com.example.myapplication.data.MyDatabaseInitializer
import com.example.myapplication.domain.FooodRepositpry
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(private val context: Context) {
    @Provides
    fun provideAppDatabase(): FoodDatabase {
        return databaseBuilder(context, FoodDatabase::class.java, "food_database").addCallback(
            MyDatabaseInitializer(context)
        ).build()
    }

    @Provides
    fun provideMainDao(appDatabase: FoodDatabase): FoodDao {
        return appDatabase.foodDao()
    }

    @Provides
    fun provideRepository(foodDao: FoodDao): FooodRepositpry {
        return FoodRepositoryImpl(foodDao)
    }
}