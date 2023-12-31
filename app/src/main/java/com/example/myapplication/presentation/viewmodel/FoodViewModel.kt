package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FoodRepository
import com.example.myapplication.data.DbModel.CategoryModel
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodViewModel(val foodRepository: FoodRepository) : ViewModel() {

    private val _category = MutableStateFlow<List<CategoryModel>>(emptyList())
    val category: StateFlow<List<CategoryModel>> = _category

    private val _selectedCategory = MutableStateFlow<CategoryModel?>(null)
    val selectedCategory: StateFlow<CategoryModel?> = _selectedCategory

    private val _orders = MutableStateFlow<List<OrderModel>>(emptyList())
    val orders: StateFlow<List<OrderModel>> = _orders

    /*private val _foods = MutableStateFlow<List<FoodModel>>(emptyList())
    val foods: StateFlow<List<FoodModel>> = _foods*/

    val foods2=foodRepository.foods
    init {
      /*  viewModelScope.launch { //this: CoroutineScope
            foodRepository.foods.flowOn(Dispatchers.IO).collect { foods: List<FoodModel> ->
                _foods.update { foods  }

        }*/

    }

    // افزودن یک مورد به لیست سفارشات
    fun addToOrder(food: FoodModel, categoryModel: CategoryModel) {
        viewModelScope.launch {
            val existingOrder = _orders.value.find { it.food_Id == food.id }

            if (existingOrder != null) {
                existingOrder.count += 1.0
                existingOrder.final_price = existingOrder.base_price * existingOrder.count
                _orders.emit(_orders.value.toMutableList())
            } else {
                val newOrder = OrderModel(
                    food_Id = food.id,
                    food_cat = categoryModel.name,
                    count = 1.0,
                    food_name = food.name,
                    base_price = food.price,
                    final_price = food.price
                )
                val currentOrders = _orders.value.toMutableList()
                currentOrders.add(newOrder)
                _orders.emit(currentOrders)
            }
        }
    }


    // انتخاب یک دسته بندی
    fun selectCategory(category: CategoryModel) {
        _selectedCategory.value = category
    }

    // بارگذاری دسته بندی‌ها از ریپازیتوری
/*
    fun loadCategories() {
        viewModelScope.launch {
            _category.emit(foodRepository.getCategories())
        }
    }

    // بارگذاری لیست سفارشات از ریپازیتوری
    fun loadOrders() {
        viewModelScope.launch {
            _orders.emit(foodRepository.getOrders())
        }
    }
*/

    // دیگر قسمت‌های کلاس...
}
