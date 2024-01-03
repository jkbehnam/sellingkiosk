package com.example.myapplication.Models

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FoodModel::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        private var instance: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            if (instance == null) {
                synchronized(FoodDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_database"
                    )
                        .addCallback(MyDatabaseInitializer(context.applicationContext as Application))
                        .build()
                }
            }
            return instance!!
        }
    }
}