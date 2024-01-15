package com.example.myapplication.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.data.DbModel.CategoryModel
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel
import com.example.myapplication.domain.FoodRepositpry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FoodViewModelTest {

    // Mock dependencies
    @Mock
    private lateinit var mockFoodRepository: FoodRepositpry

    // The ViewModel you want to test
    private lateinit var foodViewModel: FoodViewModel
    // Mock Observer for LiveData
    @Mock
    private lateinit var mockldtest: Observer<Int>

    @Before
    fun setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this)

        // Create ViewModel instance with mocked repository
        foodViewModel = FoodViewModel(mockFoodRepository)

        foodViewModel.ldtest.observeForever(mockldtest)
    }

    @Test
    fun testAddToOrder() {
        foodViewModel.incrementCounter()

        // Verify that the LiveData has been updated with the expected value
        assertEquals(1, foodViewModel.ldtest.value)
    }
}