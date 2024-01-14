package com.example.myapplication.data

import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.domain.FooodRepositpry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val foodDao: FoodDao):FooodRepositpry {
    override  fun getAllFoods(): Flow<List<FoodModel>> {
      return  foodDao.getAllFoods()
    }
}