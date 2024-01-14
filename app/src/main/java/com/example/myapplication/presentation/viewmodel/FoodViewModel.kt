package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DbModel.CategoryModel
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel
import com.example.myapplication.domain.FoodRepositpry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FoodViewModel(val foodRepository: FoodRepositpry) : ViewModel() {

    private val _category = MutableStateFlow<List<CategoryModel>>(emptyList())
    val category: StateFlow<List<CategoryModel>> = _category

    private val _selectedCategory = MutableStateFlow<CategoryModel?>(null)
    val selectedCategory: StateFlow<CategoryModel?> = _selectedCategory

    private val _orders = MutableLiveData<List<OrderModel>>(emptyList())
    val orders: LiveData<List<OrderModel>> = _orders

    /*private val _foods = MutableStateFlow<List<FoodModel>>(emptyList())
    val foods: StateFlow<List<FoodModel>> = _foods*/
    init {
      /*  viewModelScope.launch { //this: CoroutineScope
            foodRepository.foods.flowOn(Dispatchers.IO).collect { foods: List<FoodModel> ->
                _foods.update { foods  }

        }*/

    }

    // افزودن یک مورد به لیست سفارشات
    fun addToOrder(food:FoodModel,categoryModel: CategoryModel,plus:Int){
        val existingOrder = _orders.value?.find { it.food_Id == food.id }

        if (existingOrder != null) {
            // اگر وجود داشته باشد، تعداد را افزایش دهید
            existingOrder.count += plus

            existingOrder.final_price= existingOrder.base_price* existingOrder.count
            if (existingOrder.count==0.0){
                val currentOrders = _orders.value.orEmpty().toMutableList()
                currentOrders.removeAll{ it.food_Id == existingOrder.food_Id }
                _orders.value= currentOrders
                return
            }
            _orders.value= _orders.value
        } else {
            // اگر وجود نداشته باشد، یک OrderModel جدید ایجاد کنید و به لیست اضافه کنید
            val newOrder = OrderModel(
                food_Id = food.id,
                food_cat = categoryModel.name, // یا هر مقداری که مرتبط با دسته بندی غذا است
                count = 1.0,
                food_name = food.name,
                base_price = food.price,
                final_price = food.price
            )
            val currentOrders = _orders.value.orEmpty().toMutableList()
            currentOrders.add(newOrder)
            _orders.value = currentOrders
        }}


    /*    private val _food = MutableLiveData<List<FoodModel>>()
        val food: LiveData<List<FoodModel>> = _food*/
   val foods = foodRepository.getAllFoods()
}


    // انتخاب یک دسته بندی


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

