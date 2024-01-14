package com.example.myapplication.data

import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.domain.FoodRepository1
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl(val foodDao: FoodDao):FoodRepository1 {
    override suspend fun getFoods(): Flow<List<FoodModel>> {
        return getFoodFromDB()
    }

    suspend fun getFoodFromDB(): Flow<List<FoodModel>> {
       return foodDao.getAllFoods()
    }
}