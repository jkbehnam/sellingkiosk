package com.example.myapplication.domain

import com.example.myapplication.data.DbModel.FoodModel
import kotlinx.coroutines.flow.Flow

class GetFoodsUseCase(val foodRepository: FooodRepositpry) {
    suspend fun execute(): Flow<List<FoodModel>>? =foodRepository.getAllFoods()

}