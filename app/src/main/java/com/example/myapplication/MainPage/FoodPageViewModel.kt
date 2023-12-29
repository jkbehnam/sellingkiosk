package com.example.myapplication.MainPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Models.CategoryModel
import com.example.myapplication.Models.FoodModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class FoodPageViewModel : ViewModel() {
    private val _category = MutableLiveData<List<CategoryModel>>()
    val category: LiveData<List<CategoryModel>> = _category

    private val _selectedCategory = MutableLiveData<CategoryModel>()
    val selectedCategory: LiveData<CategoryModel> = _selectedCategory

    private val _food = MutableLiveData<FoodModel>()
    val food: LiveData<FoodModel> = _food

    init {
        fetchCategoties()
    }


    private fun fetchCategoties() {
        viewModelScope.launch {

            try {



            }catch (e:Exception){}

        }
    }

}