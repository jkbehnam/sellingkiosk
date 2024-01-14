package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

class GetFoodUseCase {
   fun execute(fooodRepositpry: FooodRepositpry): Flow<List<FoodModel>>?=fooodRepositpry.getAllFoods()
}