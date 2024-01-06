package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

@Dao
public interface FoodDao {
    @Insert
    suspend fun insert(foodModel: FoodModel)
    @Insert
    suspend fun insertItems(foodModels: List<FoodModel>)

    @Query("SELECT * FROM food_table")
     fun getAllFoods(): Flow<List<FoodModel>>
}

