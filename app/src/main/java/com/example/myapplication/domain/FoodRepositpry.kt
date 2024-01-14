package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

public interface FoodRepositpry {
   public fun getAllFoods(): Flow<List<FoodModel>>

}