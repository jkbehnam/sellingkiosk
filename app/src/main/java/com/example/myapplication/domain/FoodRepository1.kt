package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

interface FoodRepository1 {
    suspend fun getFoods(): Flow<List<FoodModel>>?
}