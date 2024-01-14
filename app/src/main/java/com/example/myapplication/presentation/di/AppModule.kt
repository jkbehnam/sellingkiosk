package com.example.myapplication.presentation.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


// AppModule.java
@Module
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {

        return context.applicationContext
    }
}

