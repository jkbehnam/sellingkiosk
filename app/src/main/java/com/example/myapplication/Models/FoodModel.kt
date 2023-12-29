package com.example.myapplication.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodModel(val id:Int=0, val name:String, val imv:String, val ingredients:String, val price:String):Parcelable
