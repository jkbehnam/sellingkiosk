package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

 interface FoodRepositpry {
    fun getAllFoods(): Flow<List<FoodModel>>

}