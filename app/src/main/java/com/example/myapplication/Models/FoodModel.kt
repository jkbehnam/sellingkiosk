package com.example.myapplication.Models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity("food_table")
@Parcelize
data class FoodModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val catgId:Int,
    val name: String,
    val imv: String,
    val ingredients: String,
    val price: String
) : Parcelable
