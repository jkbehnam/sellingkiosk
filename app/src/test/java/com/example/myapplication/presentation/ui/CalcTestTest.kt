package com.example.myapplication.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalcTestTest {


    @Before
    fun setUp() {
        myCalc = CalcTest()


    }

    lateinit var myCalc: CalcTest


    @Test
    fun calculateCircumference_radiusGiven() {

        val result = myCalc.calculateCircumference(2.1)
        assertThat(result).isEqualTo(13.188)

    }
}