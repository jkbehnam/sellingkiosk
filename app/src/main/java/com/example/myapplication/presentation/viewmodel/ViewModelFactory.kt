package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.FoodRepositpry

class ViewModelFactory(val repository:FoodRepositpry):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java))
            return FoodViewModel(repository) as T
        throw IllegalArgumentException("unknown")
    }
}