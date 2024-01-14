package com.example.myapplication.presentation.di

import android.content.Context
import com.example.myapplication.domain.FoodRepository1
import dagger.Component

@Component(modules = [FoodModule::class,DataBaseModule::class,AppModule::class])
interface FoodComponent {
    fun inject(context: Context)
    fun getFoodRepository(): FoodRepository1
}