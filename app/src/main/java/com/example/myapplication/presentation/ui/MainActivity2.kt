package com.example.myapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.domain.FoodRepositpry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MainActivity2 : BaseActivity() {
    var foodRepositpry: FoodRepositpry? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val x: Flow<List<FoodModel>>? = foodRepositpry?.getAllFoods()
        if (x != null) {
            runBlocking(Dispatchers.IO) {
                Log.i("beeehnnnamm", x.first().toString())

            }
        }

    }
    companion object {
        fun removedecimal(number: String): String {
            val resultText = number.replace("\\.0*$".toRegex(), "")
            return resultText

        }
    }
}