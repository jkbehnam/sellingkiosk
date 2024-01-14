package com.example.myapplication.presentation.di

import android.content.Context
import com.example.myapplication.presentation.di.DataBaseModule
import com.example.myapplication.presentation.ui.FoodMainFragment
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DataBaseModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    fun inject(context: Context?) // دیگر متدها و injectors
}