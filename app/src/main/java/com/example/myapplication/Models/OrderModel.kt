package com.example.myapplication.Models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class OrderModel(
    @PrimaryKey(autoGenerate = true)
    val order_id: Int =0,
    var food_Id:Int,
    var food_cat:String,
    var count:Double,
    val food_name: String,
    val base_price: Double,
    var final_price:Double,
)
