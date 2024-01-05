package com.example.myapplication.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
     fun getAllFoods():LiveData<List<FoodModel>>
}