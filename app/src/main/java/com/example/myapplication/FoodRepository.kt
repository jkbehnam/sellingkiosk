package com.example.myapplication

import androidx.lifecycle.LiveData
import com.example.myapplication.Models.FoodDao

class FoodRepository(val dao: FoodDao) {

    val foods=dao.getAllFoods()
}