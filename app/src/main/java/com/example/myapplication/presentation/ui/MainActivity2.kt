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

//        val appComponent: AppComponent
//        appComponent = DaggerAppComponent.builder()
//            .build()
//        appComponent.inject(this)

        val x: Flow<List<FoodModel>>? = foodRepositpry?.getAllFoods()
        if (x != null) {
            runBlocking(Dispatchers.IO) {
                Log.i("beeehnnnamm", x.first().toString())

            }
        }
      /*  window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE)
*/
    }
}