package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Models.CategoryModel
import com.example.myapplication.Models.FoodModel
import com.example.myapplication.Models.OrderModel

class FoodViewModel(val foodRepository: FoodRepository) : ViewModel() {
    private val _category = MutableLiveData<List<CategoryModel>>()
    val category: LiveData<List<CategoryModel>> = _category

    private val _selectedCategory = MutableLiveData<CategoryModel>()
    val selectedCategory: LiveData<CategoryModel> = _selectedCategory

    private val _orders = MutableLiveData<List<OrderModel>>()
    val orders: LiveData<List<OrderModel>> = _orders

fun addToOrder(food:FoodModel,categoryModel: CategoryModel){
    val existingOrder = _orders.value?.find { it.food_Id == food.id }

    if (existingOrder != null) {
        // اگر وجود داشته باشد، تعداد را افزایش دهید
        existingOrder.count += 1.0
        existingOrder.final_price= existingOrder.base_price* existingOrder.count
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
    val foods = foodRepository.foods
}