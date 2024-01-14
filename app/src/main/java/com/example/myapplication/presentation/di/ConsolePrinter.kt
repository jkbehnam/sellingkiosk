package com.example.myapplication.presentation.di

import javax.inject.Inject

class ConsolePrinter @Inject constructor() : Printer {
    override fun print(message: String) {
        println(message)
    }
}