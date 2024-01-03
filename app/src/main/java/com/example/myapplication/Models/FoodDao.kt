package com.example.myapplication.Models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
public interface FoodDao {
    @Insert
    suspend fun insert(foodModel: FoodModel)
    @Insert
    suspend fun insertItems(foodModels: List<FoodModel>)

    @Query("SELECT * FROM food_table")
    suspend fun getAllFoods():List<FoodModel>
}