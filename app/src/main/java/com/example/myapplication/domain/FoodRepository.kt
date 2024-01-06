package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.FoodDao
import kotlinx.coroutines.flow.Flow

class FoodRepository(val dao: FoodDao) {

    val foods: Flow<List<FoodModel>> = dao.getAllFoods()

}