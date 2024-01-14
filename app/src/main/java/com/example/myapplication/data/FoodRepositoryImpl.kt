package com.example.myapplication.data

import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.domain.FoodRepositpry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val foodDao: FoodDao):FoodRepositpry {
    override  fun getAllFoods(): Flow<List<FoodModel>> {
      return  foodDao.getAllFoods()
    }
}