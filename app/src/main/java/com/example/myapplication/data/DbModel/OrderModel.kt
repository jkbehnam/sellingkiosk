package com.example.myapplication.data.DbModel

import androidx.room.PrimaryKey

data class OrderModel(
    @PrimaryKey(autoGenerate = true)
    val order_id: Int =0,
    var food_Id:Int,
    var food_cat:String,
    var count:Double=0.0,
    val food_name: String,
    val base_price: Double,
    var final_price:Double,
)
