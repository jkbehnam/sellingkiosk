package com.example.myapplication.presentation.di

import com.example.myapplication.presentation.ui.FoodMainFragment
import dagger.Component

@Component(modules = [AppModule::class,RoomModule::class])
interface AppComponent {
    fun inject(fragment: FoodMainFragment)
}