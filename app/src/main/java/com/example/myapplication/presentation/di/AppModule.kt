package com.example.myapplication.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    fun providePrinter(): Printer {
        return ConsolePrinter()
    }
}